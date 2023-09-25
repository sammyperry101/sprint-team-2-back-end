package org.kainos.ea.api;

import org.kainos.ea.cli.JobFamily;
import org.kainos.ea.client.FailedToGetJobFamilyException;
import org.kainos.ea.client.JobFamilyNotFoundException;
import org.kainos.ea.db.JobFamilyDao;
import java.sql.SQLException;
import java.util.List;

public class JobFamilyService {
    private JobFamilyDao jobFamilyDao;

    public JobFamilyService(JobFamilyDao jobFamilyDao) {
        this.jobFamilyDao = jobFamilyDao;
    }

    public List<JobFamily> getFamilyByCapability(int capabilityID) throws JobFamilyNotFoundException, FailedToGetJobFamilyException {
        try {
            List<JobFamily> jobFamilies = jobFamilyDao.getFamilyByCapability(capabilityID);

            if (jobFamilies.isEmpty()) {
                throw new JobFamilyNotFoundException();
            }

            return jobFamilies;
        } catch (SQLException e) {
            System.err.println(e);

            throw new FailedToGetJobFamilyException();
        }
    }
}