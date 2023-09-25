package org.kainos.ea.client;

public class FailedToGetJobRole extends Exception {
    @Override
    public String getMessage() {
        return "Failed to get Job Role.";
    }
}
