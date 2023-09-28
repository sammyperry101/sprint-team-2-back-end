package org.kainos.ea.api;

import org.kainos.ea.cli.Family;
import org.kainos.ea.client.FailedToGetFamiliesException;
import org.kainos.ea.client.FailedToGetFamilyException;
import org.kainos.ea.client.FamilyDoesNotExistException;
import org.kainos.ea.db.FamilyDao;

import java.sql.SQLException;
import java.util.List;

public class FamilyService {
    FamilyDao familyDao;

    public FamilyService(FamilyDao familyDao) {
        this.familyDao = familyDao;
    }

    public List<Family> getFamilies() throws FailedToGetFamiliesException{
        try {
            return familyDao.getFamilies();
        } catch (SQLException e){
            throw new FailedToGetFamiliesException();
        }
    }

    public Family getFamilyById(int id) throws FailedToGetFamilyException, FamilyDoesNotExistException {
        try{
            Family family = familyDao.getFamilyById(id);

            if(family == null) throw new FamilyDoesNotExistException();

            return family;
        } catch (SQLException e){
            throw new FailedToGetFamilyException();
        }
    }
}
