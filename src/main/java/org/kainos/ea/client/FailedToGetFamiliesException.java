package org.kainos.ea.client;

public class FailedToGetFamiliesException extends Throwable {
    @Override
    public String getMessage(){
        return "Failed to get families";
    }
}
