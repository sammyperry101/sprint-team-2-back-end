package org.kainos.ea.client;

public class JobRolesNotFoundException extends Throwable{
    @Override
    public String getMessage(){
        return "No job roles were found!";
    }
}
