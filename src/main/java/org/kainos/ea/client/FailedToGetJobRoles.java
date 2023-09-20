package org.kainos.ea.client;

public class FailedToGetJobRoles extends Throwable{
    @Override
    public String getMessage(){
        return "Failed to get job roles!";
    }
}
