package org.kainos.ea.cli;

public class JobRole {
    private int roleID;
    private String name;
    private String jobSpec;
    private String responsibilities;
    private String sharepointLink;
    private int capability;

    private int bandID;
    private int familyID;

    public JobRole(int roleID, String name, String jobSpec, String responsibilities,
                   String sharepointLink, int capability, int bandID, int familyID) {
        this.roleID = roleID;
        this.name = name;
        this.jobSpec = jobSpec;
        this.responsibilities = responsibilities;
        this.sharepointLink = sharepointLink;
        this.capability = capability;
        this.bandID = bandID;
        this.familyID = familyID;
    }

    public int getRoleID() {
        return roleID;
    }

    public void setRoleID(int roleID) {
        this.roleID = roleID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJobSpec() {
        return jobSpec;
    }

    public void setJobSpec(String jobSpec) {
        this.jobSpec = jobSpec;
    }

    public String getResponsibilities() {
        return responsibilities;
    }

    public void setResponsibilities(String responsibilities) {
        this.responsibilities = responsibilities;
    }

    public String getSharepointLink() {
        return sharepointLink;
    }

    public void setSharepointLink(String sharepointLink) {
        this.sharepointLink = sharepointLink;
    }

    public int getCapability() {
        return capability;
    }

    public void setCapability(int capability) {
        this.capability = capability;
    }

    public int getBandID() {
        return bandID;
    }

    public void setBandID(int bandID) {
        this.bandID = bandID;
    }

    public int getFamilyID() {
        return familyID;
    }

    public void setFamilyID(int familyID) {
        this.familyID = familyID;
    }
}
