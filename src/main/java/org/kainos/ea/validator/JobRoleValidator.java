package org.kainos.ea.validator;

import org.kainos.ea.cli.JobRoleEditRequest;
import org.kainos.ea.cli.JobRoleRequest;
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


    public boolean validateJobRole(JobRoleRequest jobRoleRequest) throws SQLException, InvalidNameException,
            InvalidSharepointLinkException, InvalidBandIDException, InvalidFamilyIDException, NullFieldException {
        if (jobRoleRequest == null) {
            throw new NullFieldException();
        }
        if (jobRoleRequest.getRoleName() == null) {
            throw new NullFieldException();
        }
        if (jobRoleRequest.getJob_Spec() == null) {
            throw new NullFieldException();
        }
        if (jobRoleRequest.getResponsibilities() == null) {
            throw new NullFieldException();
        }
        if (jobRoleRequest.getSharepointLink() == null) {
            throw new NullFieldException();
        }
        if (jobRoleRequest.getRoleName().length() > 70) {
            throw new InvalidNameException();
        }
        if (!(jobRoleRequest.getSharepointLink()
                .matches("^(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]"))) {
            throw new InvalidSharepointLinkException();
        }
        if (bandDao.checkBandIDExists(jobRoleRequest.getBandName()) <= -1) { //Check the BandID is valid
            throw new InvalidBandIDException();
        }
        if (familyDao.checkFamilyIDExists(jobRoleRequest.getCapabilityName()) <= 0) {
            throw new InvalidFamilyIDException();
        }
        return true;
    }
}
