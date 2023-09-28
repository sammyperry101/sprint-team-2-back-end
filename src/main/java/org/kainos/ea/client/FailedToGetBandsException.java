package org.kainos.ea.client;

public class FailedToGetBandsException extends Exception {
    @Override
    public String getMessage(){
        return "Failed to get Bands";
    }
}
