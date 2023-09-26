package org.kainos.ea.api;

import org.kainos.ea.cli.JobRole;
import org.kainos.ea.cli.JobRoleRequest;
import org.kainos.ea.client.*;
import org.kainos.ea.core.BandValidator;
import org.kainos.ea.core.FamilyValidator;
import org.kainos.ea.core.JobRoleValidator;
import org.kainos.ea.db.BandDao;
import org.kainos.ea.db.FamilyDao;
import org.kainos.ea.db.JobRoleDao;

import java.sql.SQLException;
import java.util.List;

public class JobRoleService {
    private JobRoleDao jobRoleDao;

    public JobRoleService(JobRoleDao jobRoleDao) {
        this.jobRoleDao = jobRoleDao;
    }
    public List<JobRole> viewRoles() throws JobRolesNotFoundException, FailedToGetJobRoles {
        try{
            List<JobRole> roles = jobRoleDao.getJobRoles();

            if(roles.isEmpty()){
                throw new JobRolesNotFoundException();
            }

            return roles;
        } catch(SQLException e){
            System.err.println(e.getMessage());

            throw new FailedToGetJobRoles();
        }
    }

    public Integer createJobRole(JobRoleRequest jobRole)
            throws FailedToCreateJobRoleException, InvalidJobRoleException
    {
        try{
            BandValidator bandValidator = new BandValidator(new BandService(new BandDao()));
            FamilyValidator familyValidator = new FamilyValidator(new FamilyService(new FamilyDao()));

            JobRoleValidator jobRoleValidator = new JobRoleValidator(bandValidator, familyValidator);

            String invalidJobRoleMessage = jobRoleValidator.isValidJobRole(jobRole);

            if(invalidJobRoleMessage != null){
                throw new InvalidJobRoleException(invalidJobRoleMessage);
            }

            return jobRoleDao.createJobRole(jobRole);
        } catch (SQLException | FailedToGetBandException | FailedToGetFamilyException e){
            throw new FailedToCreateJobRoleException();
        }
    }
}