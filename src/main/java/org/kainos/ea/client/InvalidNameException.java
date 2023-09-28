package org.kainos.ea.client;

public class InvalidNameException extends Exception {
    @Override
    public String getMessage() {
        return "Name is greater than 70 characters.";
    }
}
