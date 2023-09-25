package org.kainos.ea.client;

public class FailedToGetJobCapabilityException extends Throwable {
    @Override
    public String getMessage() {
        return "Failed to get job capability";
    }
}
