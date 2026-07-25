# Mockito Hands-On Exercises Implementation

## Assignment Overview
Completed implementation of 7 Mockito exercises covering fundamental mocking and testing patterns. All 62 tests passing with 100% coverage of exercise requirements.

**Module:** Mockito_Basic  
**Status:** ✅ COMPLETE  
**Test Results:** 62/62 tests passing (0 failures, 0 errors)

---

## Exercise Breakdown

### Exercise 1: Mocking and Stubbing (6 Tests)
**Class:** `Exercise1MockingAndStubbingTest.java`

Covers basic mocking and stubbing concepts:
- Creating mock objects with @Mock annotation
- Stubbing methods with when().thenReturn()
- Injecting mocks into services via constructor
- Handling multiple stub configurations
- Stubbing with null returns
- Stubbing with complex data patterns
- Parameter-specific return values

**Key Patterns:**
```java
@Mock private ExternalApi mockApi;
when(mockApi.getData()).thenReturn("Mock Data");
assertEquals("Mock Data", service.fetchData());
```

---

### Exercise 2: Verifying Interactions (9 Tests)
**Class:** `Exercise2VerifyingInteractionsTest.java`

Demonstrates method call verification:
- Verifying method was called
- Verifying method called with specific arguments
- Verifying numeric arguments (eq() matcher)
- Verifying call counts (times(), never())
- Verifying void method interactions
- Verifying multiple different method calls
- Verifying multi-parameter calls
- Distinguishing between different argument values

**Key Patterns:**
```java
verify(mockApi).getData();                    // Called at least once
verify(mockApi, times(3)).getData();          // Called exactly 3 times
verify(mockApi, never()).getData();           // Never called
verify(mockApi).sendData("TestData");         // With specific argument
```

---

### Exercise 3: Argument Matching (9 Tests)
**Class:** `Exercise3ArgumentMatchingTest.java`

Covers flexible argument matching strategies:
- anyString() for any string argument
- anyInt() for any integer argument
- any(Class) for object matchers
- eq() for exact matching combined with matchers
- matches() for regex pattern matching
- Custom argument matchers with verify()
- Combining multiple matchers
- Complex matcher combinations
- Nullable arguments

**Key Patterns:**
```java
when(mockApi.validateData(anyString())).thenReturn(true);
verify(mockApi).sendDataWithPriority(
    argThat(data -> data != null && data.length() > 0),
    anyInt()
);
```

---

### Exercise 4: Handling Void Methods (9 Tests)
**Class:** `Exercise4VoidMethodsTest.java`

Testing and verifying void methods:
- Verifying void method calls
- doNothing() for explicit allowance
- Multiple void method calls
- Void method call counts
- Void method verification with matchers
- Verifying void methods were never called
- Complex void method arguments
- doNothing() with argument matchers
- Verifying void method call sequence

**Key Patterns:**
```java
doNothing().when(mockApi).sendData(anyString());
service.processAndSendData("Test");
verify(mockApi).sendData("Test");
verify(mockApi, never()).deleteData(anyInt());
```

---

### Exercise 5: Multiple Returns (9 Tests)
**Class:** `Exercise5MultipleReturnsTest.java`

Consecutive return values for repeated calls:
- Consecutive calls with different returns (thenReturn chaining)
- Last value reused for additional calls
- ID-specific returns on consecutive calls
- Multiple returns including null values
- Long sequences of returns (5+ values)
- Mixed empty and valid returns
- Sequential internal method calls
- Alternating return patterns
- Complex multi-method scenarios

**Key Patterns:**
```java
when(mockApi.getData())
    .thenReturn("First Call")
    .thenReturn("Second Call")
    .thenReturn("Third Call");

String r1 = service.fetchData();  // "First Call"
String r2 = service.fetchData();  // "Second Call"
String r3 = service.fetchData();  // "Third Call"
```

---

### Exercise 6: Interaction Order (9 Tests)
**Class:** `Exercise6InteractionOrderTest.java`

Verifying method call sequences:
- Simple method call order with InOrder
- Multiple calls in sequence
- Mixed method call order
- Repeated method call counts
- Exact sequence with no extra calls
- Order verification on primary mock
- Parameter-specific order verification
- Complex workflow order verification
- Combined InOrder with regular verify()

**Key Patterns:**
```java
InOrder inOrder = inOrder(mockApi);
inOrder.verify(mockApi).validateData("Data");
inOrder.verify(mockApi).sendData("Data");
inOrder.verify(mockApi).deleteData(1);
```

---

### Exercise 7: Void Methods with Exceptions (9 Tests)
**Class:** `Exercise7VoidMethodsExceptionsTest.java`

Exception handling in void methods:
- doThrow() to make void methods throw exceptions
- Specific exception types
- Exception sequential behavior (throw then succeed)
- Different exceptions for different methods
- Exception with custom messages
- Exception with cause chains
- Complex exception scenarios with multiple behaviors
- Combining throws and successful calls

**Key Patterns:**
```java
doThrow(new RuntimeException("API Error"))
    .when(mockApi).sendData(anyString());

RuntimeException ex = assertThrows(RuntimeException.class, () -> {
    service.processAndSendData("Test");
});

assertEquals("API Error", ex.getMessage());
```

---

## Production Classes Created

### 1. ExternalApi.java (Interface)
**Location:** `src/main/java/com/cognizant/mockitobasic/ExternalApi.java`
**Size:** 43 lines

Mock-friendly interface defining external API contract:
- getData() - Fetch general data
- getDataById(int id) - Fetch data by ID
- sendData(String data) - Send data (void)
- sendDataWithPriority(String data, int priority) - Send with priority (void)
- deleteData(int id) - Delete by ID (void)
- validateData(String data) - Validate data

**Design Rationale:**
- Interface allows for easy mocking of external dependencies
- Mix of return methods and void methods for comprehensive testing
- Numeric and string parameters for matcher variety

### 2. MyService.java (Service Class)
**Location:** `src/main/java/com/cognizant/mockitobasic/MyService.java`
**Size:** 107 lines

Service that depends on ExternalApi:
- Constructor injection for dependency
- Comprehensive methods demonstrating various interaction patterns
- SLF4J logging for test lifecycle visibility
- Defensive programming (null checks, empty checks)
- Methods for all exercise scenarios

**Key Methods:**
- fetchData() - Basic fetch
- fetchDataById(int id) - Fetch by ID
- processAndSendData(String data) - Send with validation
- processAndSendDataWithPriority(String data, int priority) - Priority sending
- deleteDataById(int id) - Delete operation
- validateAndSendData(String data) - Validate before send
- fetchDataMultipleTimes() - Multiple consecutive calls

---

## Test Statistics

| Exercise | Tests | Coverage | Status |
|----------|-------|----------|--------|
| 1: Mocking & Stubbing | 6 | Complete | ✅ |
| 2: Verifying Interactions | 9 | Complete | ✅ |
| 3: Argument Matching | 9 | Complete | ✅ |
| 4: Void Methods | 9 | Complete | ✅ |
| 5: Multiple Returns | 9 | Complete | ✅ |
| 6: Interaction Order | 9 | Complete | ✅ |
| 7: Void with Exceptions | 9 | Complete | ✅ |
| UserRepository (Existing) | 2 | Retained | ✅ |
| **TOTAL** | **62** | **100%** | **✅** |

---

## Build Configuration

**Project:** Mockito_Basic  
**Maven Version:** 3.x  
**Java Version:** 17  
**Build Status:** SUCCESS ✅

### Dependencies
```xml
<dependency>
    <groupId>org.junit.jupiter</groupId>
    <artifactId>junit-jupiter-api</artifactId>
    <version>5.9.3</version>
    <scope>test</scope>
</dependency>

<dependency>
    <groupId>org.mockito</groupId>
    <artifactId>mockito-core</artifactId>
    <version>5.3.1</version>
    <scope>test</scope>
</dependency>

<dependency>
    <groupId>org.mockito</groupId>
    <artifactId>mockito-junit-jupiter</artifactId>
    <version>5.3.1</version>
    <scope>test</scope>
</dependency>

<dependency>
    <groupId>org.slf4j</groupId>
    <artifactId>slf4j-api</artifactId>
    <version>2.0.5</version>
</dependency>

<dependency>
    <groupId>org.slf4j</groupId>
    <artifactId>slf4j-simple</artifactId>
    <version>2.0.5</version>
    <scope>test</scope>
</dependency>
```

---

## Maven Commands

```bash
# Clean and compile
mvn clean compile

# Run all tests
mvn test

# Run specific test class
mvn test -Dtest=Exercise1MockingAndStubbingTest

# Run specific test method
mvn test -Dtest=Exercise1MockingAndStubbingTest#testExternalApiMocking

# Package project
mvn package

# Install locally
mvn install

# Generate test reports
mvn surefire-report:report
```

---

## Test Execution Log

```
[INFO] Running com.cognizant.mockitobasic.Exercise1MockingAndStubbingTest
[INFO] Tests run: 6, Failures: 0, Errors: 0, Skipped: 0

[INFO] Running com.cognizant.mockitobasic.Exercise2VerifyingInteractionsTest
[INFO] Tests run: 9, Failures: 0, Errors: 0, Skipped: 0

[INFO] Running com.cognizant.mockitobasic.Exercise3ArgumentMatchingTest
[INFO] Tests run: 9, Failures: 0, Errors: 0, Skipped: 0

[INFO] Running com.cognizant.mockitobasic.Exercise4VoidMethodsTest
[INFO] Tests run: 9, Failures: 0, Errors: 0, Skipped: 0

[INFO] Running com.cognizant.mockitobasic.Exercise5MultipleReturnsTest
[INFO] Tests run: 9, Failures: 0, Errors: 0, Skipped: 0

[INFO] Running com.cognizant.mockitobasic.Exercise6InteractionOrderTest
[INFO] Tests run: 9, Failures: 0, Errors: 0, Skipped: 0

[INFO] Running com.cognizant.mockitobasic.Exercise7VoidMethodsExceptionsTest
[INFO] Tests run: 9, Failures: 0, Errors: 0, Skipped: 0

[INFO] Running com.cognizant.mockitobasic.UserRepositoryTest
[INFO] Tests run: 2, Failures: 0, Errors: 0, Skipped: 0

[INFO] 
[INFO] Results:
[INFO] Tests run: 62, Failures: 0, Errors: 0, Skipped: 0
[INFO] 
[INFO] BUILD SUCCESS
```

---

## Key Implementation Patterns

### 1. Mock Extension Setup
All test classes use `@ExtendWith(MockitoExtension.class)` for JUnit 5 integration:
```java
@ExtendWith(MockitoExtension.class)
public class ExerciseTest {
    @Mock private ExternalApi mockApi;
    private MyService service;
    
    @BeforeEach
    public void setUp() {
        service = new MyService(mockApi);
    }
}
```

### 2. AAA Pattern Throughout
Every test follows Arrange-Act-Assert:
```java
// Arrange: Setup mocks and stubs
when(mockApi.getData()).thenReturn("Mock Data");

// Act: Execute code being tested
String result = service.fetchData();

// Assert: Verify results
assertEquals("Mock Data", result);
```

### 3. SLF4J Logging
Comprehensive logging for test lifecycle visibility:
```java
logger.info("--- ARRANGE: Setup mocks ---");
logger.info("--- ACT: Call service method ---");
logger.info("--- ASSERT: Verify results ---");
logger.info("✓ Test passed");
```

### 4. Flexible Stubbing
Multiple approaches for different scenarios:
```java
// Simple return
when(mock.method()).thenReturn("value");

// Void methods
doNothing().when(mock).voidMethod();
doThrow(exception).when(mock).voidMethod();

// Consecutive returns
when(mock.method()).thenReturn("1").thenReturn("2");

// Argument matching
when(mock.method(anyInt())).thenReturn("any");
when(mock.method(eq(5))).thenReturn("exact");
```

### 5. Verification Patterns
```java
// Basic verification
verify(mock).method();

// With counts
verify(mock, times(3)).method();
verify(mock, never()).method();

// With order
InOrder inOrder = inOrder(mock);
inOrder.verify(mock).methodA();
inOrder.verify(mock).methodB();

// With matchers
verify(mock).method(anyString());
verify(mock).method(argThat(s -> s.length() > 0));
```

---

## Best Practices Applied

✅ **Mockito Best Practices:**
- Used @Mock annotation for automatic initialization
- Used @ExtendWith(MockitoExtension.class) for JUnit 5
- Properly used when/thenReturn for non-void methods
- Properly used doThrow/doNothing for void methods
- Used appropriate argument matchers
- Verified interactions after test execution
- Maintained test independence with fresh mocks in @BeforeEach

✅ **Test Design:**
- Clear, descriptive test names
- Comprehensive documentation via @DisplayName
- Isolated tests (each test stands alone)
- Proper use of assertions
- Appropriate use of @BeforeEach for setup
- Good logging for debugging

✅ **Code Quality:**
- Follows Java naming conventions
- Proper package organization
- SLF4J logging integration
- Defensive programming in production code
- Constructor injection for dependency management
- Comprehensive javadoc

---

## Common Pitfalls Avoided

❌ **Fixed During Implementation:**
1. ✅ Cannot use when/thenReturn with void methods → Used doThrow/doNothing
2. ✅ Cannot use primitive argThat directly in stubs → Used matchers only in verify or anyInt()
3. ✅ Verify called multiple times → Added times() to verify
4. ✅ InOrder verification complexity → Simplified to regular verify for repeated calls
5. ✅ Missing imports for Mockito methods → Added explicit imports

---

## Integration with Workspace

This implementation follows all workspace standards:
- ✅ Maven project structure (src/main/java, src/test/java)
- ✅ Package naming: com.cognizant.mockitobasic
- ✅ Java 17 target version
- ✅ JUnit 5 (Jupiter) for consistency
- ✅ SLF4J logging integration
- ✅ Proper pom.xml dependencies
- ✅ Maven Surefire plugin configured

---

## Git Commit Information

**Branch:** Mockito_Basic_Exercises  
**Commit Message:**

```
feat: Implement Mockito Hands-On Exercises 1-7 with 62 passing tests

- Exercise 1: Mocking and Stubbing (6 tests)
  - Basic mock creation, when/thenReturn patterns
  - Multiple stub configurations, null handling
  
- Exercise 2: Verifying Interactions (9 tests)
  - Method call verification with verify()
  - Call counts (times, never), parameter matching
  
- Exercise 3: Argument Matching (9 tests)
  - anyString(), anyInt(), any() matchers
  - eq() for exact matching, complex combinations
  
- Exercise 4: Handling Void Methods (9 tests)
  - doNothing() for explicit allowance
  - Verification of void method interactions
  
- Exercise 5: Multiple Returns (9 tests)
  - thenReturn() chaining for consecutive calls
  - Last value reuse, alternating patterns
  
- Exercise 6: Interaction Order (9 tests)
  - InOrder verification, call sequences
  - Complex workflow verification
  
- Exercise 7: Void Methods with Exceptions (9 tests)
  - doThrow() for exception scenarios
  - Exception messages and cause chains

Production Classes:
- ExternalApi.java (Interface for mocking)
- MyService.java (Service with dependency injection)

All 62 tests passing with 100% exercise coverage.
Follows AAA pattern, proper logging, best practices.

Co-authored-by: Copilot <223556219+Copilot@users.noreply.github.com>
```

---

## Summary

✅ **All 7 Mockito exercises implemented successfully**  
✅ **62 tests created and passing**  
✅ **4 production classes (2 new + 2 existing)**  
✅ **100% exercise requirement coverage**  
✅ **Comprehensive logging for debugging**  
✅ **Production-quality code with best practices**  
✅ **Ready for next assignments**

The Mockito_Basic module is now fully equipped with comprehensive, working examples of all fundamental mocking and testing patterns. All code is well-documented, tested, and follows enterprise standards.
