package org.kainos.ea.client;

public class InvalidCapabilityNameException extends Exception {
    @Override
    public String getMessage() {
        return "This is not a valid Family";
    }
}
