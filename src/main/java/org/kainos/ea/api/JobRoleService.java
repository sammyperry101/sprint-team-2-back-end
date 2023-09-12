package org.kainos.ea.api;

import org.kainos.ea.cli.JobRole;
import org.kainos.ea.client.FailedToDeleteJobRoleException;
import org.kainos.ea.client.JobRoleDoesNotExistException;
import org.kainos.ea.db.JobRoleDao;

import java.sql.SQLException;

public class JobRoleService {
    private JobRoleDao jobRoleDao;

    public JobRoleService(JobRoleDao jobRoleDao) {
        this.jobRoleDao = jobRoleDao;
    }

    public int deleteRole(int id) throws JobRoleDoesNotExistException, FailedToDeleteJobRoleException {
        try {
            JobRole jobRole = jobRoleDao.getRoleById(id);

            System.out.println(id);
            System.out.println(jobRole);

            if (jobRole == null) {
                throw new JobRoleDoesNotExistException();
            }

            int rowsDeleted = jobRoleDao.deleteRole(id);

            return rowsDeleted;
        } catch (SQLException e) {
            System.err.println(e.getMessage());

            throw new FailedToDeleteJobRoleException();
        }
    }
}
