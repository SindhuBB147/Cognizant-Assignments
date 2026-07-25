# JUnit Advanced Testing - Exercises 1-4

## Assignment Information

**Module:** JUnit_Advanced_Testing
**Package:** `com.cognizant.junitadvanced`
**Total Exercises:** 4
**Total Tests Created:** 35
**Build Status:** ✅ SUCCESS
**Test Status:** ✅ 35/35 PASSED

---

## 📋 Assignment Overview

This assignment covers fundamental JUnit 5 testing concepts including:
1. Setting up JUnit in a project
2. Writing basic unit tests
3. Using various assertions
4. Applying the Arrange-Act-Assert (AAA) pattern with test fixtures

### ⚠️ Important Note: JUnit 4 vs JUnit 5

The assignment PDF uses **JUnit 4** syntax. However, this implementation uses **JUnit 5 (Jupiter)** as per best practices and project standards.

**Annotation Mapping (JUnit 4 → JUnit 5):**
- `@Test` → `@Test` (identical)
- `@Before` → `@BeforeEach`
- `@After` → `@AfterEach`
- `@BeforeClass` → `@BeforeAll` (static)
- `@AfterClass` → `@AfterAll` (static)

---

## 📂 Files Created

### Main Classes (Production Code)

#### 1. Calculator.java
**Location:** `src/main/java/com/cognizant/junitadvanced/Calculator.java`
**Purpose:** Provides basic arithmetic operations for testing

**Methods:**
- `add(int a, int b)` - Adds two integers
- `subtract(int a, int b)` - Subtracts two integers
- `multiply(int a, int b)` - Multiplies two integers
- `divide(int a, int b)` - Divides two integers (throws exception on zero divisor)
- `isPositive(int number)` - Checks if number is positive
- `isNegative(int number)` - Checks if number is negative

**Key Features:**
- Comprehensive JavaDoc comments
- Exception handling for division by zero
- Clear method naming

---

### Test Classes

#### 1. Exercise1SetupJUnitTest.java
**Location:** `src/test/java/com/cognizant/junitadvanced/Exercise1SetupJUnitTest.java`
**Purpose:** Demonstrates JUnit 5 setup and configuration

**Tests:**
- `testJUnitSetup()` - Verifies JUnit is properly configured
- `testMethodDiscovery()` - Shows test method discovery
- `testMultipleTestExecution()` - Demonstrates multiple independent tests

**Key Learning Points:**
- JUnit 5 annotations (@Test, @DisplayName)
- Test method naming conventions
- Test independence

---

#### 2. Exercise2BasicTestsTest.java
**Location:** `src/test/java/com/cognizant/junitadvanced/Exercise2BasicTestsTest.java`
**Purpose:** Demonstrates writing basic JUnit tests with setup methods

**Tests:**
- `testAddPositiveNumbers()` - Tests addition with positive numbers
- `testAddNegativeNumbers()` - Tests addition with negative numbers
- `testSubtract()` - Tests subtraction
- `testMultiply()` - Tests multiplication
- `testDivide()` - Tests division
- `testIsPositive()` - Tests positive number identification

**Key Learning Points:**
- @BeforeEach for test fixture initialization
- Basic test structure
- Meaningful test names
- Clear assertions

---

#### 3. Exercise3AssertionsTest.java
**Location:** `src/test/java/com/cognizant/junitadvanced/Exercise3AssertionsTest.java`
**Purpose:** Demonstrates various JUnit assertions

**Tests:**
- `testAssertions()` - Comprehensive assertion demonstration (from assignment)
- `testAssertEquals()` - Tests assertEquals with different types
- `testBooleanAssertions()` - Tests assertTrue/assertFalse
- `testNullAssertions()` - Tests assertNull/assertNotNull
- `testAssertThrows()` - Tests exception assertion
- `testIdentityAssertions()` - Tests assertSame/assertNotSame
- `testArrayAssertions()` - Tests assertArrayEquals
- `testMultipleAssertions()` - Combines multiple assertions

**Assertions Covered:**
```java
assertEquals(expected, actual)        // Value equality
assertTrue(condition)                 // Condition is true
assertFalse(condition)                // Condition is false
assertNull(object)                    // Object is null
assertNotNull(object)                 // Object is not null
assertThrows(exception, executable)   // Exception is thrown
assertArrayEquals(expected, actual)   // Arrays are equal
assertSame(expected, actual)          // Same reference
assertNotSame(expected, actual)       // Different references
```

---

#### 4. Exercise4AAAPatternTest.java
**Location:** `src/test/java/com/cognizant/junitadvanced/Exercise4AAAPatternTest.java`
**Purpose:** Demonstrates AAA Pattern with setup/teardown methods and logging

**Tests:**
1. `testAddPositiveNumbersAAA()` - Addition with AAA pattern
2. `testSubtractNegativeResultAAA()` - Subtraction with negative result
3. `testMultiplyAAA()` - Multiplication operation
4. `testDivideAAA()` - Division operation
5. `testDivideByZeroAAA()` - Exception testing
6. `testMultipleAssertionsAAA()` - Multiple assertions
7. `testTestIndependenceAAA()` - Test independence verification

**Key Features:**
- @BeforeEach - Initializes fresh fixtures for each test
- @AfterEach - Cleans up resources after each test
- SLF4J logging to show test lifecycle
- Clear Arrange-Act-Assert separation
- Comprehensive comments

**AAA Pattern Explanation:**
```java
@Test
void testExample() {
    // ARRANGE: Set up test data
    int a = 5;
    int b = 3;
    
    // ACT: Execute code being tested
    int result = calculator.add(a, b);
    
    // ASSERT: Verify results
    assertEquals(8, result);
}
```

---

## 🛠️ Test Execution Summary

### Build Commands

**Clean build:**
```bash
mvn clean
```

**Compile:**
```bash
mvn compile
```

**Run all tests:**
```bash
mvn test
```

**Run specific test class:**
```bash
mvn test -Dtest=Exercise1SetupJUnitTest
```

**Run specific test method:**
```bash
mvn test -Dtest=Exercise4AAAPatternTest#testAddPositiveNumbersAAA
```

**Package:**
```bash
mvn package
```

---

## ✅ Test Results

### Overall Summary
- **Total Tests:** 35
- **Passed:** 35 ✅
- **Failed:** 0
- **Errors:** 0
- **Skipped:** 0

### Breakdown by Test Class

| Test Class | Tests | Passed | Failed | Errors |
|------------|-------|--------|--------|--------|
| Exercise1SetupJUnitTest | 3 | 3 | 0 | 0 |
| Exercise2BasicTestsTest | 6 | 6 | 0 | 0 |
| Exercise3AssertionsTest | 8 | 8 | 0 | 0 |
| Exercise4AAAPatternTest | 7 | 7 | 0 | 0 |
| StringValidatorTest (existing) | 5 | 5 | 0 | 0 |
| StringValidatorTest$IsEmptyTests | 5 | 5 | 0 | 0 |
| StringValidatorTest$IsNumericTests | 6 | 6 | 0 | 0 |

---

## 📊 Expected Output

### Console Output Example:
```
[INFO] Running com.cognizant.junitadvanced.Exercise1SetupJUnitTest
[INFO] Tests run: 3, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.015 s

[INFO] Running com.cognizant.junitadvanced.Exercise2BasicTestsTest
[INFO] Tests run: 6, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.032 s

[INFO] Running com.cognizant.junitadvanced.Exercise3AssertionsTest
[INFO] Tests run: 8, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.045 s

[INFO] Running com.cognizant.junitadvanced.Exercise4AAAPatternTest
[INFO] Tests run: 7, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.056 s

[INFO] Results:
[INFO] Tests run: 35, Failures: 0, Errors: 0, Skipped: 0

[INFO] BUILD SUCCESS
```

### SLF4J Log Output (from Exercise4):
```
========== SETUP: Initializing test fixtures ==========
Calculator instance created
Test fixtures initialized and ready for test execution
>>> TEST 1: Testing addition of positive numbers
ARRANGE: Setting up test data - operands: 5 and 3
ACT: Calling calculator.add(5, 3)
ACT: Result received: 8
ASSERT: Verifying result equals 8
ASSERT: Test passed!
========== TEARDOWN: Cleaning up after test ==========
Test 1 executed, calculator = com.cognizant.junitadvanced.Calculator@3c9754d8
Resources cleaned up and test fixtures reset
```

---

## 🔍 Key Concepts Demonstrated

### 1. JUnit Setup
- Dependency configuration in pom.xml
- Test class creation
- Test method naming conventions
- Test discovery mechanism

### 2. Basic Tests
- Simple test structure
- Multiple test methods
- Test independence
- Failure messages

### 3. Assertions
- Value comparison (assertEquals)
- Boolean checks (assertTrue/assertFalse)
- Null checks (assertNull/assertNotNull)
- Exception testing (assertThrows)
- Reference equality (assertSame/assertNotSame)
- Array comparison (assertArrayEquals)

### 4. AAA Pattern
- **Arrange:** Set up test data and fixtures
- **Act:** Execute the code being tested
- **Assert:** Verify the results
- Benefits: Clear test structure, easy to understand, maintainable

### 5. Test Fixtures & Lifecycle
- @BeforeEach: Run before each test (fresh state)
- @AfterEach: Run after each test (cleanup)
- Benefits: Code reuse, isolation, clean state
- Difference from @Before (JUnit 4): Automatically handles per-test isolation

---

## 📚 Best Practices Implemented

✅ **Test Naming:** All test methods have clear, descriptive names
✅ **DisplayName:** Used @DisplayName for better readability in reports
✅ **Comments:** All methods and complex logic documented
✅ **AAA Pattern:** Clear separation of Arrange-Act-Assert
✅ **Test Independence:** Each test can run independently
✅ **Failure Messages:** All assertions include failure messages
✅ **Fixture Management:** Proper setup and teardown
✅ **Logging:** SLF4J logging to show lifecycle
✅ **Single Responsibility:** Each test focuses on one behavior
✅ **Edge Cases:** Tests include positive, negative, and exception cases

---

## 🔄 Dependencies

**Configured in pom.xml:**
- JUnit 5 Jupiter API 5.9.3
- JUnit 5 Jupiter Engine 5.9.3
- JUnit 5 Parameterized Tests 5.9.3
- SLF4J API 2.0.7
- SLF4J Simple 2.0.7

---

## 📝 Project Structure

```
JUnit_Advanced_Testing/
├── pom.xml
├── src/
│   ├── main/java/com/cognizant/junitadvanced/
│   │   ├── Calculator.java
│   │   └── StringValidator.java
│   ├── test/java/com/cognizant/junitadvanced/
│   │   ├── Exercise1SetupJUnitTest.java
│   │   ├── Exercise2BasicTestsTest.java
│   │   ├── Exercise3AssertionsTest.java
│   │   ├── Exercise4AAAPatternTest.java
│   │   └── StringValidatorTest.java
│   └── resources/
└── target/
```

---

## 🚀 Quick Start

### Navigate to project:
```bash
cd "JUnit, Mockito and SLF4J\JUnit_Advanced_Testing"
```

### Compile and test:
```bash
mvn clean test
```

### Run a specific test class:
```bash
mvn test -Dtest=Exercise4AAAPatternTest
```

---

## ✨ Notes

1. **JUnit 5 vs JUnit 4:** This implementation uses JUnit 5 (Jupiter) which is the current standard and provides better features like:
   - Better parameterized testing
   - Nested test classes
   - Custom annotations
   - Junit Platform foundation

2. **Logging:** Exercise 4 uses SLF4J for logging to demonstrate the test lifecycle clearly. This helps understand when setup and teardown occur.

3. **Calculator Class:** Comprehensive implementation with multiple operations to demonstrate different test scenarios.

4. **All Tests Independent:** Each test can run in isolation and multiple times without side effects due to @BeforeEach initialization.

---

## ✅ Verification Checklist

- [x] All 4 exercises implemented
- [x] Calculator class created with all required methods
- [x] 4 comprehensive test classes created
- [x] AAA pattern clearly demonstrated
- [x] All JUnit assertions covered
- [x] Setup/Teardown methods implemented
- [x] SLF4J logging added
- [x] JavaDoc comments on all classes/methods
- [x] All 35 tests pass
- [x] Build succeeds with zero errors
- [x] No warnings or issues
- [x] Best practices followed

---

**Status:** ✅ COMPLETE AND VERIFIED
**Created:** July 25, 2026
**Tests Passed:** 35/35
