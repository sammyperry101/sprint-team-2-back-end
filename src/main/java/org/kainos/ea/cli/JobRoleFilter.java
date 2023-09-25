package org.kainos.ea.cli;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class JobRoleFilter {

    private String roleNameFilter;
    private String bandNameFilter;
    private String capabilityNameFilter;

    @JsonCreator
    JobRoleFilter(
            @JsonProperty("roleNameFilter") String roleNameFilter,
            @JsonProperty
}
