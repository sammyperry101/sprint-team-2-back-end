package org.kainos.ea.api;

import org.kainos.ea.client.JobRoleDoesNotExistException;
import org.kainos.ea.client.FailedToDeleteJobRoleException;
import org.kainos.ea.client.FailedToGetJobRole;
import org.kainos.ea.client.FailedToGetJobRoles;
import org.kainos.ea.client.JobRolesNotFoundException;
import org.kainos.ea.db.JobRoleDao;

import java.sql.SQLException;
import org.kainos.ea.cli.JobRoleRequest;
import java.util.List;

public class JobRoleService {
    private JobRoleDao jobRoleDao;

    public JobRoleService(JobRoleDao jobRoleDao) {
        this.jobRoleDao = jobRoleDao;
    }

    public int deleteRole(int id) throws JobRoleDoesNotExistException, FailedToDeleteJobRoleException {
        try {
            JobRoleRequest jobRole = jobRoleDao.getRoleById(id);

            if (jobRole == null) {
                throw new JobRoleDoesNotExistException();
            }

            return jobRoleDao.deleteRole(id);
        } catch (SQLException e) {
            System.err.println(e.getMessage());

            throw new FailedToDeleteJobRoleException();
        }
    }

    public JobRoleRequest getRoleById(int id) throws JobRoleDoesNotExistException, FailedToGetJobRole {
        try {
            JobRoleRequest jobRole = jobRoleDao.getRoleById(id);

            if (jobRole == null) {
                throw new JobRoleDoesNotExistException();
            }

            return jobRole;
        } catch (SQLException e) {
            System.err.println(e.getMessage());

            throw new FailedToGetJobRole();
        }
    }

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
}