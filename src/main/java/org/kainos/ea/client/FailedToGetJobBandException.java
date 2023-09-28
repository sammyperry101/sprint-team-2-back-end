package org.kainos.ea.client;

public class FailedToGetJobBandException extends Throwable{
    @Override
    public String getMessage(){
        return "Failed to get job band(s)";
    }
}
