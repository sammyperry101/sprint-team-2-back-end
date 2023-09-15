package org.kainos.ea.client;

public class JobSpec {
    private String jobSpec;
    private String sharepointLink;

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

    public JobSpec(String jobSpec, String sharepointLink) {
        this.jobSpec = jobSpec;
        this.sharepointLink = sharepointLink;
    }
}
