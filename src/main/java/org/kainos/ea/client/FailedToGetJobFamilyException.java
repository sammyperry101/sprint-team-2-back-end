package org.kainos.ea.client;

public class FailedToGetJobFamilyException extends Throwable {
    @Override
    public String getMessage() {
        return "Failed to get job family";
    }
}
