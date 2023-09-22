package org.kainos.ea.cli;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.glassfish.jersey.server.JSONP;

public class JobFamily {
    private int familyID;
    private int capabilityID;
    private String name;

    @JsonCreator
    public JobFamily(int familyID, int capabilityID, String name) {
        this.familyID = familyID;
        this.capabilityID = capabilityID;
        this.name = name;
    }

    public int getFamilyID() {
        return familyID;
    }

    public void setFamilyID(int familyID) {
        this.familyID = familyID;
    }

    public int getCapabilityID() {
        return capabilityID;
    }

    public void setCapabilityID(int capabilityID) {
        this.capabilityID = capabilityID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
