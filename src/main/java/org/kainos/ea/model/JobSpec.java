package org.kainos.ea.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class JobSpec {
    private String jobSpec;
    private String sharepointLink;
    private int roleId;
    private String name;

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

    public String getSharepointLink() {
        return sharepointLink;
    }

    public void setSharepointLink(String sharepointLink) {
        this.sharepointLink = sharepointLink;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    @JsonCreator
    public JobSpec(@JsonProperty("jobSpec") String jobSpec,
                   @JsonProperty("sharepointLink") String sharepointLink,
                   @JsonProperty("RoleID") int roleId,
                   @JsonProperty("Name") String name) {
        this.jobSpec = jobSpec;
        this.sharepointLink = sharepointLink;
        this.roleId = roleId;
        this.name = name;
    }
}
