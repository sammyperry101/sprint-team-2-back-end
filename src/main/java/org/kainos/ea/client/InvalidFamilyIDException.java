package org.kainos.ea.client;

public class InvalidFamilyIDException extends Exception {
    @Override
    public String getMessage() {
        return "This is not a valid Family";
    }
}
