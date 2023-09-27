package org.kainos.ea.validator;

import org.kainos.ea.cli.JobRoleEditRequest;
import org.kainos.ea.client.InvalidBandIDException;
import org.kainos.ea.client.InvalidFamilyIDException;
import org.kainos.ea.client.InvalidSharepointLinkException;
import org.kainos.ea.client.InvalidNameException;
import org.kainos.ea.client.NullFieldException;
import org.kainos.ea.db.BandDao;
import org.kainos.ea.db.DatabaseConnector;
import org.kainos.ea.db.FamilyDao;

import java.sql.SQLException;

public class JobRoleValidator {

    private DatabaseConnector databaseConnector;
    BandDao bandDao = new BandDao(databaseConnector);
    FamilyDao familyDao = new FamilyDao(databaseConnector);


    public boolean validateJobRole(JobRoleEditRequest jobRoleEditRequest) throws SQLException, InvalidNameException,
            InvalidSharepointLinkException, InvalidBandIDException, InvalidFamilyIDException, NullFieldException {
        if (jobRoleEditRequest == null) {
            throw new NullFieldException();
        }
        if (jobRoleEditRequest.getName() == null) {
            throw new NullFieldException();
        }
        if (jobRoleEditRequest.getJob_Spec() == null) {
            throw new NullFieldException();
        }
        if (jobRoleEditRequest.getResponsibilities() == null) {
            throw new NullFieldException();
        }
        if (jobRoleEditRequest.getSharepointLink() == null) {
            throw new NullFieldException();
        }
        if (jobRoleEditRequest.getName().length() > 70) {
            throw new InvalidNameException();
        }
        if (!(jobRoleEditRequest.getSharepointLink()
                .matches("^(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]"))) {
            throw new InvalidSharepointLinkException();
        }
        if (bandDao.checkBandIDExists(jobRoleEditRequest.getBandId()) <= -1) { //Check the BandID is valid
            throw new InvalidBandIDException();
        }
        if (familyDao.checkFamilyIDExists(jobRoleEditRequest.getFamilyId()) <= 0) {
            throw new InvalidFamilyIDException();
        }
        return true;
    }
}
