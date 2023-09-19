package org.kainos.ea.cli;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class CapabilityFamilyJoinRequest {
    private int familyID;
    private int capabilityID;
    private String name;

    @JsonCreator
    public CapabilityFamilyJoinRequest(@JsonProperty("familyID") int familyID,
                                       @JsonProperty("capabilityID") int capabilityID,
                                       @JsonProperty("name") String name) {
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
