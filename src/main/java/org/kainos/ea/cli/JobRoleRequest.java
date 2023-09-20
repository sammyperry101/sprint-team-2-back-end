package org.kainos.ea.cli;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class JobRoleRequest {
    private String name;
    private String jobSpec;
    private String responsibilities;
    private String sharepointLink;
    private int bandID;
    private int familyID;

    @JsonCreator
    public JobRoleRequest(
            @JsonProperty("name") String name,
            @JsonProperty("jobSpec") String jobSpec,
            @JsonProperty("responsibilities") String responsibilities,
            @JsonProperty("sharepointLink") String sharepointLink,
            @JsonProperty("bandId") int bandID,
            @JsonProperty("familyId") int familyID) {
        this.name = name;
        this.jobSpec = jobSpec;
        this.responsibilities = responsibilities;
        this.sharepointLink = sharepointLink;
        this.bandID = bandID;
        this.familyID = familyID;
    }

    public String getName(){
        return this.name;
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
