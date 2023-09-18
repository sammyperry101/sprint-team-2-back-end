package org.kainos.ea.api;

import org.kainos.ea.client.FailedToGetJobSpecException;
import org.kainos.ea.client.JobSpecDoesNotExist;
import org.kainos.ea.model.JobSpec;
import org.kainos.ea.db.JobSpecDAO;

import java.sql.SQLException;

public class JobSpecService {
    JobSpecDAO jobSpecDAO;

    public JobSpecService(JobSpecDAO jobSpecDAO) {
        this.jobSpecDAO = jobSpecDAO;
    }

    public JobSpec getJobSpecByRoleId(int roleId) throws FailedToGetJobSpecException, JobSpecDoesNotExist {
        try{
            JobSpec jobSpec = jobSpecDAO.getJobSpecByRoleId(roleId);

            if(jobSpec == null){
                throw new JobSpecDoesNotExist();
            }

            return jobSpec;
        } catch (SQLException e){
            System.err.println(e.getMessage());
            throw new FailedToGetJobSpecException();
        }
    }
}
