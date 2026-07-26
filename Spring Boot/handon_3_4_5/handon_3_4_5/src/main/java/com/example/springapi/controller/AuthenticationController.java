package com.example.springapi.controller;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
@RestController
public class AuthenticationController {
    private static final String SECRET =
            "mysecretkeymysecretkeymysecretkey12";
    SecretKey key = Keys.hmacShaKeyFor(
            SECRET.getBytes(StandardCharsets.UTF_8));
    @GetMapping("/authenticate")
    public Map<String, String> authenticate(HttpServletRequest request) {
        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();
        String user = getUser(request);
        String token = generateJwt(user);
        Map<String, String> map = new HashMap<>();
        map.put("status", "Authentication Successful");
        map.put("token", token);
        return map;
    }
    private String generateJwt(HttpServletRequest request) {
        String authorization =
                request.getHeader("Authorization");
        String encoded =
                authorization.substring(6);
        byte[] decoded =
                Base64.getDecoder().decode(encoded);
        String credentials =
                new String(decoded, StandardCharsets.UTF_8);
        return credentials.split(":",2)[0];
    }
    private String getUser(HttpServletRequest request) {
        String authorization =
                request.getHeader("Authorization");
        String encoded =
                authorization.substring(6);
        byte[] decoded =
                Base64.getDecoder().decode(encoded);
        String credentials =
                new String(decoded, StandardCharsets.UTF_8);
        return credentials.split(":",2)[0];
    }
    private String generateJwt(String user){
        return Jwts.builder()
                .setSubject(user)
                .setIssuedAt(new Date())
                .setExpiration(
                        new Date(
                                System.currentTimeMillis()
                                        + 1200000))
                .signWith(key)
                .compact();
    }
}