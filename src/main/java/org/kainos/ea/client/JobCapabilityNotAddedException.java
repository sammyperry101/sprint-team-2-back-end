package org.kainos.ea.client;

public class JobCapabilityNotAddedException extends Throwable{
    @Override
    public String getMessage(){
        return "Job Capability was not added";
    }
}
