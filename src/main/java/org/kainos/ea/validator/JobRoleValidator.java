package org.kainos.ea.validator;

import org.kainos.ea.cli.JobRoleRequest;
import org.kainos.ea.client.InvalidBandNameException;
import org.kainos.ea.client.InvalidCapabilityNameException;
import org.kainos.ea.client.InvalidSharepointLinkException;
import org.kainos.ea.client.InvalidNameException;
import org.kainos.ea.client.NullFieldException;
import org.kainos.ea.db.BandDao;
import org.kainos.ea.db.DatabaseConnector;
import org.kainos.ea.db.CapabilityDao;

import java.sql.SQLException;

public class JobRoleValidator {

    private DatabaseConnector databaseConnector;
    BandDao bandDao = new BandDao(databaseConnector);
    CapabilityDao capabilityDao = new CapabilityDao(databaseConnector);


    public boolean validateJobRole(JobRoleRequest jobRoleRequest) throws SQLException, InvalidNameException,
            InvalidSharepointLinkException, InvalidBandNameException, InvalidCapabilityNameException, NullFieldException {
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
        if (bandDao.checkBandNameExists(jobRoleRequest.getBandName()) <= -1) { //Check the BandID is valid
            throw new InvalidBandNameException();
        }
        if (capabilityDao.checkCapabilityNameExists(jobRoleRequest.getCapabilityName()) <= 0) {
            throw new InvalidCapabilityNameException();
        }
        return true;
    }
}
