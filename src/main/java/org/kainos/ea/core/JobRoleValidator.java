package org.kainos.ea.core;

import org.kainos.ea.cli.JobRolePostRequest;
import org.kainos.ea.client.FailedToGetBandException;
import org.kainos.ea.client.FailedToGetFamilyException;

public class JobRoleValidator {
    BandValidator bandValidator;
    FamilyValidator familyValidator;

    public JobRoleValidator(
            BandValidator bandValidator,
            FamilyValidator familyValidator)
    {
        this.bandValidator = bandValidator;
        this.familyValidator = familyValidator;
    }

    public String isValidJobRole(JobRolePostRequest jobRole)
            throws FailedToGetBandException, FailedToGetFamilyException
    {

        boolean bandExists =  bandValidator.bandExists(jobRole.getBandID());
        if(!bandExists) return "Band does not exist.";

        boolean familyExists = familyValidator.familyExists(jobRole.getFamilyID());
        if(!familyExists) return "Family does not exist.";

        if(jobRole.getName().trim().isEmpty()) return "Name cannot be empty";

        if(jobRole.getName().length() > 70) return "Name can not be longer than 70 characters";

        return null;
    }
}
