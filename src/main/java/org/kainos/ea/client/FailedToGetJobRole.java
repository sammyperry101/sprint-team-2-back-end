package org.kainos.ea.client;

public class FailedToGetJobRole extends Throwable{
    @Override
    public String getMessage(){
        return "Failed to get job role!";
    }
}
