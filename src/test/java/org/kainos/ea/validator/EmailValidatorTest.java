package org.kainos.ea.validator;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.kainos.ea.cli.RegisterRequest;
import org.kainos.ea.cli.Role;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class EmailValidatorTest {
    EmailValidator emailValidator = new EmailValidator();
    @Test
    void passwordValidator_ShouldReturnErrorString_WhenEmailIsWrongFormat(){
        RegisterRequest registerRequest = new RegisterRequest("invalidEmail", "Password$", Role.ADMIN);
        String result = emailValidator.validateUserEmail(registerRequest);

        assertEquals("Invalid email address", result, "Expected an error message for a wrong email");
    }

    @Test
    void passwordValidator_ShouldReturnEmptyString_WhenEmailAndPasswordIsCorrect(){
        RegisterRequest registerRequest = new RegisterRequest("user2@user.com", "Password$", Role.ADMIN);
        String result = emailValidator.validateUserEmail(registerRequest);

        assertEquals("", result, "Expected an empty string for a valid password");
    }


}
