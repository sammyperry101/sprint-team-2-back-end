package org.kainos.ea.client;

public class FailedToGetJobRolesException extends Throwable{
    @Override
    public String getMessage(){
        return "Failed to get job roles!";
    }
}
