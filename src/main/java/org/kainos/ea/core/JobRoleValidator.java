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
    BandService bandService;
    FamilyService familyService;

    public JobRoleValidator(
            BandValidator bandValidator,
            BandService bandService,
            FamilyService familyService)
    {
        this.bandValidator = bandValidator;
        this.bandService = bandService;
        this.familyService = familyService;
    }

    public String isValidJobRole(JobRoleRequest jobRole)
            throws FailedToGetBandException, BandDoesNotExistException,
            FamilyDoesNotExistException, FailedToGetFamilyException
    {
        //todo no need to call getBandById and then validator
        //todo Either create object and then validate or just getById and then handle exceptions
        // Validate BandID
        Band band = bandService.getBandById(jobRole.getBandID());
        String invalidBand =  bandValidator.isValidBand(band);

        if(invalidBand != null){
            return invalidBand;
        }

        //todo Create family validator
        Family family = familyService.getFamilyById(jobRole.getFamilyID());

        return null;
    }
}
