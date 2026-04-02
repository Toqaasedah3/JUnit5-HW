package main.najah.test;

import static org.junit.jupiter.api.Assertions.*;

import java.time.Duration;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import main.najah.code.Calculator;

@TestMethodOrder(OrderAnnotation.class)
@DisplayName("Test Calculator Class")
public class TestCalculator {

    Calculator calc;

    // ========================
    // 🔹 Lifecycle Hooks
    // ========================

    @BeforeAll
    static void beforeAll() {
        System.out.println("BeforeAll: setup complete");
    }

    @AfterAll
    static void afterAll() {
        System.out.println("AfterAll: all tests finished");
    }

    @BeforeEach
    void setUp() {
        calc = new Calculator();
        System.out.println("BeforeEach: calculator created");
    }

    @AfterEach
    void tearDown() {
        System.out.println("AfterEach: test done");
    }

    // ========================
    // 🔹 add() Tests
    // ========================

    @Test
    @Order(1)
    @DisplayName("Add multiple numbers")
    void testAddValid() {
        int result = calc.add(1, 2, 3);
        assertEquals(6, result);
        assertNotEquals(5, result);
    }

    @ParameterizedTest
    @Order(2)
    @DisplayName("Parameterized test for add()")
    @CsvSource({
        "1,2,3",
        "5,5,10",
        "-1,1,0"
    })
    void testAddParameterized(int a, int b, int expected) {
        int result = calc.add(a, b);
        assertEquals(expected, result);
        assertTrue(result == expected);
    }

    // ========================
    // 🔹 divide() Tests
    // ========================

    @Test
    @Order(3)
    @DisplayName("Divide valid numbers")
    void testDivideValid() {
        int result = calc.divide(10, 2);
        assertEquals(5, result);
        assertFalse(result == 0);
    }

    @Test
    @Order(4)
    @DisplayName("Divide by zero should throw exception")
    void testDivideByZero() {
        assertThrows(ArithmeticException.class, () -> calc.divide(10, 0));
    }

    // ========================
    // 🔹 factorial() Tests
    // ========================

    @Test
    @Order(5)
    @DisplayName("Factorial valid input")
    void testFactorialValid() {
        int result = calc.factorial(5);
        assertEquals(120, result);
        assertTrue(result > 0);
    }

    @Test
    @Order(6)
    @DisplayName("Factorial negative input should throw exception")
    void testFactorialNegative() {
        assertThrows(IllegalArgumentException.class, () -> calc.factorial(-1));
    }

    // ========================
    // 🔹 Timeout Test
    // ========================

    @Test
    @Order(7)
    @DisplayName("Test execution within timeout")
    void testTimeout() {
        assertTimeout(Duration.ofMillis(200), () -> {
            calc.add(100, 200, 300);
        });
    }

    // ========================
    // 🔹 Disabled Test (required)
    // ========================

    @Disabled("Intentional fail - fix by changing expected value to 3")
    @Test
    @Order(8)
    @DisplayName("Intentional failing test")
    void failingTest() {
        assertEquals(999, calc.add(1, 2));
    }
}