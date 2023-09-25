package org.kainos.ea.core;

import org.kainos.ea.api.FamilyService;
import org.kainos.ea.client.FailedToGetFamilyException;
import org.kainos.ea.client.FamilyDoesNotExistException;

public class FamilyValidator {
    private FamilyService familyService;
    public FamilyValidator(FamilyService familyService) {
        this.familyService = familyService;
    }

    public boolean familyExists(int familyId) throws FailedToGetFamilyException {
        try{
            return familyService.getFamilyById(familyId) != null;
        } catch (FamilyDoesNotExistException e){
            return false;
        }
    }
}
