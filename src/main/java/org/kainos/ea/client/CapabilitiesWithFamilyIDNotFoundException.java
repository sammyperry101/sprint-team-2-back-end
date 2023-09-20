package org.kainos.ea.client;

public class CapabilitiesWithFamilyIDNotFoundException extends Throwable{
    @Override
    public String getMessage(){
        return "Could not find Capabilities with FamilyID";
    }
}
