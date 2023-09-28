package org.kainos.ea.client;

public class JobBandNotFoundException extends Throwable{
    @Override
    public String getMessage(){
        return "Could not get job Band(s)";
    }
}
