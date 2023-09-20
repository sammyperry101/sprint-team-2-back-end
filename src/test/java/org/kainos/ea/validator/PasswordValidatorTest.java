package org.kainos.ea.validator;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.kainos.ea.cli.RegisterRequest;
import org.kainos.ea.cli.Role;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PasswordValidatorTest {
    PasswordValidator passwordValidator = new PasswordValidator();

    @Test
    void validator_ShouldReturnErrorString_WhenPasswordIsLessThan8Characters(){
        RegisterRequest registerRequest = new RegisterRequest("user2@user.com", "pass", Role.ADMIN);
        String result = passwordValidator.validateUser(registerRequest);

        assertEquals("Password must be at least 8 characters long", result, "Expected an error message for a short password");
    }

    @Test
    void validator_ShouldReturnErrorString_WhenPasswordHasNoUpperCaseLetters(){
        RegisterRequest registerRequest = new RegisterRequest("user2@user.com", "password", Role.ADMIN);
        String result = passwordValidator.validateUser(registerRequest);

        assertEquals("Password must contain at least one upper case letter.", result, "Expected an error message for a wrong password");
    }

    @Test
    void validator_ShouldReturnErrorString_WhenPasswordHasNoLowerCaseLetters(){
        RegisterRequest registerRequest = new RegisterRequest("user2@user.com", "PASSWORD", Role.ADMIN);
        String result = passwordValidator.validateUser(registerRequest);

        assertEquals("Password must contain at least one lower case letter.", result, "Expected an error message for a wrong password");
    }

    @Test
    void validator_ShouldReturnErrorString_WhenPasswordHasSpecialCharacters(){
        RegisterRequest registerRequest = new RegisterRequest("user2@user.com", "Password", Role.ADMIN);
        String result = passwordValidator.validateUser(registerRequest);

        assertEquals("Password must contain at least one special character (@#$%^&+=).", result, "Expected an error message for a wrong password");
    }

    @Test
    void validator_ShouldReturnErrorString_WhenEmailIsWrongFormat(){
        RegisterRequest registerRequest = new RegisterRequest("invalidEmail", "Password$", Role.ADMIN);
        String result = passwordValidator.validateUser(registerRequest);

        assertEquals("Invalid email address", result, "Expected an error message for a wrong email");
    }

    @Test
    void validator_ShouldReturnEmptyString_WhenEmailAndPasswordIsCorrect(){
        RegisterRequest registerRequest = new RegisterRequest("user2@user.com", "Password$", Role.ADMIN);
        String result = passwordValidator.validateUser(registerRequest);

        assertEquals("", result, "Expected an empty string for a valid password");
    }
}
