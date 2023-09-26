package org.kainos.ea.api;

import org.kainos.ea.cli.JobRole;
import org.kainos.ea.cli.JobRoleEditRequest;
import org.kainos.ea.cli.JobRoleRequest;
import org.kainos.ea.client.FailedToGetJobRole;
import org.kainos.ea.client.FailedToGetJobRoles;
import org.kainos.ea.client.InvalidBandIDException;
import org.kainos.ea.client.InvalidFamilyIDException;
import org.kainos.ea.client.InvalidSharepointLinkException;
import org.kainos.ea.client.JobRoleDoesNotExistException;
import org.kainos.ea.client.InvalidNameException;
import org.kainos.ea.client.JobRolesNotFoundException;
import org.kainos.ea.client.NullfieldException;
import org.kainos.ea.db.DatabaseConnector;
import org.kainos.ea.db.JobRoleDao;
import org.kainos.ea.validator.JobRoleValidator;

import java.sql.SQLException;
import java.util.List;

public class JobRoleService {
    private JobRoleDao jobRoleDao;
    private JobRoleValidator jobRoleValidator = new JobRoleValidator();

    public JobRoleService(JobRoleDao jobRoleDao) {
        this.jobRoleDao = jobRoleDao;
    }

    private DatabaseConnector databaseConnector;
    public List<JobRoleRequest> viewRoles() throws JobRolesNotFoundException, FailedToGetJobRoles {
        try{
            List<JobRoleRequest> roles = jobRoleDao.getJobRoles();

            if(roles.isEmpty()){
                throw new JobRolesNotFoundException();
            }

            return roles;
        } catch(SQLException e){
            System.err.println(e.getMessage());

            throw new FailedToGetJobRoles();
        }
    }

    public int editJobRole(int id, JobRoleEditRequest jobRoleEditRequest) throws JobRoleDoesNotExistException, FailedToGetJobRole {
        try {
            JobRole jobRoleToUpdate = jobRoleDao.getJobRoleById(id);

            if(jobRoleToUpdate == null) {
                throw new JobRoleDoesNotExistException();
            }

            if (jobRoleValidator.validateJobRole(jobRoleEditRequest)) {
                jobRoleDao.editJobRole(id, jobRoleEditRequest);
            }
            return id;
        } catch (SQLException | InvalidNameException | InvalidSharepointLinkException | InvalidBandIDException |
                 InvalidFamilyIDException | NullfieldException e) {
            System.err.println(e.getMessage());

            throw new FailedToGetJobRole();
        }
    }
}