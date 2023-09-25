package org.kainos.ea.core;

import org.kainos.ea.api.BandService;
import org.kainos.ea.api.FamilyService;
import org.kainos.ea.cli.Band;
import org.kainos.ea.cli.Family;
import org.kainos.ea.cli.JobRole;
import org.kainos.ea.cli.JobRoleRequest;
import org.kainos.ea.client.BandDoesNotExistException;
import org.kainos.ea.client.FailedToGetBandException;
import org.kainos.ea.client.FailedToGetFamilyException;
import org.kainos.ea.client.FamilyDoesNotExistException;

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

    public String isValidJobRole(JobRoleRequest jobRole)
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
