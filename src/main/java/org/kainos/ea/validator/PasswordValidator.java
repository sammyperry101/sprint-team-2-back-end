package org.kainos.ea.validator;

import org.kainos.ea.cli.RegisterRequest;

public class PasswordValidator {
    public static String validateUser(RegisterRequest user) {
        String password = user.getPassword();
        String email = user.getUsername();

        if (password.length() < 8) {
            return "Password must be at least 8 characters long";
        }

        if (!password.matches(".*[A-Z].*")) {
            return "Password must contain at least one upper case letter.";
        }

        if (!password.matches(".*[a-z].*")) {
            return "Password must contain at least one lower case letter.";
        }

        // Check if password contains at least one special character
        if (!password.matches(".*[@#$%^&+=].*")) {
            return "Password must contain at least one special character (@#$%^&+=).";
        }

        String emailPattern = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
        if (!email.matches(emailPattern)) {
            return "Invalid email address";
        }

        return "";
    }

}
