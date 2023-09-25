package org.kainos.ea.client;

public class FailedToGetBandException extends Throwable {
    @Override
    public String getMessage() {
        return "Failed to get band.";
    }
}
