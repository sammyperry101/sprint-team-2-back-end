package org.kainos.ea.validator;

import org.kainos.ea.cli.JobRole;
import org.kainos.ea.client.InvalidBandIDException;
import org.kainos.ea.client.InvalidFamilyIDException;
import org.kainos.ea.client.InvalidSharepointLinkException;
import org.kainos.ea.client.JobRoleNameTooLongException;
import org.kainos.ea.db.BandDao;
import org.kainos.ea.db.DatabaseConnector;
import org.kainos.ea.db.FamilyDao;

import java.sql.SQLException;

public class JobRoleValidator {

    private DatabaseConnector databaseConnector;
    BandDao bandDao = new BandDao(databaseConnector);
    FamilyDao familyDao = new FamilyDao(databaseConnector);


    public String validateJobRole(JobRole jobRole) throws SQLException, JobRoleNameTooLongException,
            InvalidSharepointLinkException, InvalidBandIDException, InvalidFamilyIDException {
        if (jobRole.getName().length() > 70) {
            throw new JobRoleNameTooLongException();
        }
        if (!(jobRole.getSharepointLink().matches("^(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]"))) {
            throw new InvalidSharepointLinkException();
        }
        if (bandDao.checkBandIDExists(jobRole.getBandID()) == -1) { //Check the BandID is valid
            throw new InvalidBandIDException();
        }
        if (familyDao.checkFamilyIDExists(jobRole.getFamilyID()) == -1) {
            throw new InvalidFamilyIDException();
        }
        return null;
    }
}
