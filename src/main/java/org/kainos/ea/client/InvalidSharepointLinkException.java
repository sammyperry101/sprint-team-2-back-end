package org.kainos.ea.client;

public class InvalidSharepointLinkException extends Exception {
    @Override
    public String getMessage() {
        return "This is not a valid link";
    }
}
