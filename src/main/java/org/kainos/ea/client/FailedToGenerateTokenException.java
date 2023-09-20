package org.kainos.ea.client;

public class FailedToGenerateTokenException extends RuntimeException {
    @Override
    public String getMessage(){
        return "Failed to generate token";
    }
}
