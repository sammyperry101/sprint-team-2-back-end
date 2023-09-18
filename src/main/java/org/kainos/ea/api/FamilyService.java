package org.kainos.ea.api;

import org.kainos.ea.cli.Family;
import org.kainos.ea.db.FamilyDao;

import java.sql.SQLException;

public class FamilyService {
    private FamilyDao familyDao;

    public FamilyService(FamilyDao familyDao){
        this.familyDao = familyDao;
    }

    public Family getFamilyByID(int id){
        try{
            Family family = familyDao.getFamilyByID(id);

            if(family == null){
                throw new FamilyDoesNotExistException();
            }

        } catch (SQLException e) {
            throw new FailedToGetFamilyException();
        }
    }
}
