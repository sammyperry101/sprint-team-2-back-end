package org.kainos.ea.client;

public class JobSpecDoesNotExist extends Throwable {
    @Override
    public String getMessage(){
        return "Job Role doesn't exist";
    }
}