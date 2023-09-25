package org.kainos.ea.cli;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class JobRoleRequest {
    String name;
    String jobSpec;
    String responsibilities;
    String sharepointLink;
    int bandId;
    int familyId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJob_Spec() {
        return jobSpec;
    }

    public void setJob_Spec(String jobSpec) {
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

    public int getBandId() {
        return bandId;
    }

    public void setBandId(int bandId) {
        this.bandId = bandId;
    }

    public int getFamilyId() {
        return familyId;
    }

    public void setFamilyId(int familyId) {
        this.familyId = familyId;
    }

    @JsonCreator
    public JobRoleRequest(
            @JsonProperty("name") String name,
            @JsonProperty("jobSpec") String jobSpec,
            @JsonProperty("responsibilities") String responsibilities,
            @JsonProperty("sharepointLink") String sharepointLink,
            @JsonProperty("bandId") int bandId,
            @JsonProperty("familyId") int familyId) {
        this.name = name;
        this.jobSpec = jobSpec;
        this.responsibilities = responsibilities;
        this.sharepointLink = sharepointLink;
        this.bandId = bandId;
        this.familyId = familyId;
    }
}
