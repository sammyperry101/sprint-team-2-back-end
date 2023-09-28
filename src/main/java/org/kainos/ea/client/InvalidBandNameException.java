package org.kainos.ea.client;

public class InvalidBandNameException extends Exception {
    @Override
    public String getMessage() {
        return "This is not a valid Band";
    }
}
