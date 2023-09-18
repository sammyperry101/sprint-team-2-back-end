package org.kainos.ea.cli;

public class Family {
    private int familyID;
    private String name;
    private int capabilityId;

    public Family(int familyID, String name, int capabilityId) {
        this.familyID = familyID;
        this.name = name;
        this.capabilityId = capabilityId;
    }

    public int getFamilyID() {
        return familyID;
    }

    public void setFamilyID(int familyID) {
        this.familyID = familyID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCapabilityId() {
        return capabilityId;
    }

    public void setCapabilityId(int capabilityId) {
        this.capabilityId = capabilityId;
    }
}
