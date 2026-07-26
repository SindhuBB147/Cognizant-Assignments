package com.example.springapi;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.example.springapi.controller.CountryController;
import com.example.springapi.controller.EmployeeController;
import com.jayway.jsonpath.JsonPath;

@SpringBootTest
@AutoConfigureMockMvc
class SpringapiApplicationTests {

    @Autowired
    private CountryController countryController;

    @Autowired
    private EmployeeController employeeController;

    @Autowired
    private MockMvc mvc;

    @Test
    void contextLoads() {
        assertNotNull(countryController);
        assertNotNull(employeeController);
    }

    private String getJwtToken() throws Exception {
        String basicAuthHeader = "Basic " + Base64.getEncoder().encodeToString("user:pwd".getBytes(StandardCharsets.UTF_8));
        MvcResult result = mvc.perform(get("/authenticate")
                .header("Authorization", basicAuthHeader))
                .andExpect(status().isOk())
                .andReturn();
        String response = result.getResponse().getContentAsString();
        return JsonPath.read(response, "$.token");
    }

    @Test
    void testSecurityAuthenticationSuccess() throws Exception {
        String token = getJwtToken();
        assertNotNull(token);

        // Access secure countries endpoint with JWT
        mvc.perform(get("/countries")
                .header("Authorization", "Bearer " + token))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray());
    }

    @Test
    void testSecurityAuthenticationFailure() throws Exception {
        // Access secure countries endpoint without JWT or Basic Auth -> Should be unauthorized
        mvc.perform(get("/countries"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void testCountryValidationFailure() throws Exception {
        String token = getJwtToken();
        
        // Invalid country code (1 character instead of 2)
        String invalidCountryJson = "{\"code\":\"I\",\"name\":\"India\"}";
        
        mvc.perform(post("/countries")
                .header("Authorization", "Bearer " + token)
                .contentType(MediaType.APPLICATION_JSON)
                .content(invalidCountryJson))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errors").isArray())
                .andExpect(jsonPath("$.errors[0]").value("Country code should be 2 characters"));
    }

    @Test
    void testEmployeeCRUDAndValidation() throws Exception {
        String token = getJwtToken();

        // 1. Get All Employees
        mvc.perform(get("/employees")
                .header("Authorization", "Bearer " + token))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].name").value("John Doe"));

        // 2. Put Update Employee with valid data
        String updateEmployeeJson = "{"
                + "\"id\":1,"
                + "\"name\":\"John Doe Updated\","
                + "\"salary\":60000.0,"
                + "\"permanent\":true,"
                + "\"dateOfBirth\":\"31/12/1995\","
                + "\"department\":{\"id\":1,\"name\":\"HR\"},"
                + "\"skills\":[{\"id\":1,\"name\":\"Java\"}]"
                + "}";

        mvc.perform(put("/employees")
                .header("Authorization", "Bearer " + token)
                .contentType(MediaType.APPLICATION_JSON)
                .content(updateEmployeeJson))
                .andExpect(status().isOk());

        // Verify update reflected
        mvc.perform(get("/employees")
                .header("Authorization", "Bearer " + token))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("John Doe Updated"));

        // 3. Put Update Employee with invalid name (blank) -> Validation failure
        String invalidEmployeeJson = "{"
                + "\"id\":1,"
                + "\"name\":\"\","
                + "\"salary\":60000.0,"
                + "\"permanent\":true,"
                + "\"dateOfBirth\":\"31/12/1995\","
                + "\"department\":{\"id\":1,\"name\":\"HR\"},"
                + "\"skills\":[{\"id\":1,\"name\":\"Java\"}]"
                + "}";

        mvc.perform(put("/employees")
                .header("Authorization", "Bearer " + token)
                .contentType(MediaType.APPLICATION_JSON)
                .content(invalidEmployeeJson))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errors").isArray());

        // 4. Delete Employee
        mvc.perform(delete("/employees/1")
                .header("Authorization", "Bearer " + token))
                .andExpect(status().isOk());

        // Verify deletion reflected (GET list length should be 3 instead of 4)
        mvc.perform(get("/employees")
                .header("Authorization", "Bearer " + token))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(3));
    }
}
