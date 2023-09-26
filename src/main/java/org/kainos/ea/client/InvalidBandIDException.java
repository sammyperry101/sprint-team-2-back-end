package org.kainos.ea.client;

public class InvalidBandIDException extends Exception {
    @Override
    public String getMessage() {
        return "This is not a valid Band";
    }
}
