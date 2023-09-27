package org.kainos.ea.client;

public class FailedToAddJobCapabilityException extends Throwable{
    @Override
    public String getMessage(){
        return "Failed to add job capability";
    }
}
