package org.kainos.ea.api;

import org.kainos.ea.cli.JobRole;
import org.kainos.ea.cli.JobRoleRequest;
import org.kainos.ea.client.FailedToCreateJobRoleException;
import org.kainos.ea.client.FailedToGetJobRoles;
import org.kainos.ea.client.InvalidJobRoleException;
import org.kainos.ea.client.JobRolesNotFoundException;
import org.kainos.ea.core.BandValidator;
import org.kainos.ea.core.JobRoleValidator;
import org.kainos.ea.db.BandDao;
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
            BandDao bandDAO = new BandDao();
            BandService bandService = new BandService(bandDAO);
            BandValidator bandValidator = new BandValidator(bandService);

            //todo familyDAO, Service & Validator

            JobRoleValidator jobRoleValidator = new JobRoleValidator(bandValidator, bandService);

            return jobRoleDao.createJobRole(jobRole, jobRoleValidator);
        } catch (SQLException e){
            throw new FailedToCreateJobRoleException();
        }
    }
}