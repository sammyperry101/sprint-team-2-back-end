package org.kainos.ea.client;

public class NullFieldException extends Exception {
    @Override
    public String getMessage() {
        return "1 or more fields are empty.";
    }
}
