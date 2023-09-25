package org.kainos.ea.api;

import org.kainos.ea.cli.JobRole;
import org.kainos.ea.cli.JobRoleRequest;
import org.kainos.ea.client.FailedToGetJobRoles;
import org.kainos.ea.client.JobRoleDoesNotExistException;
import org.kainos.ea.client.JobRolesNotFoundException;
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

    public void editJobRole(int id, JobRoleRequest jobRoleRequest) throws JobRoleDoesNotExistException {
        try {
            JobRole jobRoleToUpdate = jobRoleDao.getJobRoleById(id);

            if(jobRoleToUpdate == null) {
                throw new JobRoleDoesNotExistException();
            }
            jobRoleDao.editJobRole(id, jobRoleRequest);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

    }
}