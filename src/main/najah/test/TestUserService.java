package main.najah.test;

import static org.junit.jupiter.api.Assertions.*;

import java.time.Duration;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import main.najah.code.UserService;

@DisplayName("UserService Test Class")
class TestUserService {

    UserService userService;

    // 🔹 Lifecycle
    @BeforeEach
    void setUp() {
        userService = new UserService();
        System.out.println("UserService created");
    }

    // ========================
    // 🔹 isValidEmail() Tests
    // ========================

    @Test
    @DisplayName("Valid email should return true")
    void testValidEmail() {
        boolean result = userService.isValidEmail("test@gmail.com");

        assertTrue(result);
        assertNotNull(result);
    }

    @Test
    @DisplayName("Null email should return false")
    void testNullEmail() {
        boolean result = userService.isValidEmail(null);

        assertFalse(result);
        assertEquals(false, result);
    }

    @Test
    @DisplayName("Invalid email format should return false")
    void testInvalidEmail() {
        boolean result = userService.isValidEmail("invalidEmail");

        assertFalse(result);
        assertTrue(result == false);
    }

    @ParameterizedTest
    @DisplayName("Parameterized email validation test")
    @CsvSource({
        "test@gmail.com, true",
        "user@domain.com, true",
        "bademail, false",
        "test@, false",
        "'', false"
    })
    void testEmailParameterized(String email, boolean expected) {
        boolean result = userService.isValidEmail(email);

        assertEquals(expected, result);
        assertTrue(result == expected);
    }

    // ========================
    // 🔹 authenticate() Tests
    // ========================

    @Test
    @DisplayName("Correct username and password")
    void testAuthenticateValid() {
        boolean result = userService.authenticate("admin", "1234");

        assertTrue(result);
        assertEquals(true, result);
    }

    @Test
    @DisplayName("Wrong username should fail")
    void testWrongUsername() {
        boolean result = userService.authenticate("user", "1234");

        assertFalse(result);
        assertNotEquals(true, result);
    }

    @Test
    @DisplayName("Wrong password should fail")
    void testWrongPassword() {
        boolean result = userService.authenticate("admin", "0000");

        assertFalse(result);
        assertEquals(false, result);
    }

    @Test
    @DisplayName("Wrong username and password")
    void testWrongCredentials() {
        boolean result = userService.authenticate("user", "0000");

        assertFalse(result);
        assertTrue(result == false);
    }

    // ========================
    // 🔹 Timeout Test
    // ========================

    @Test
    @DisplayName("Test execution within timeout")
    void testTimeout() {
        assertTimeout(Duration.ofMillis(200), () -> {
            userService.authenticate("admin", "1234");
        });
    }
}