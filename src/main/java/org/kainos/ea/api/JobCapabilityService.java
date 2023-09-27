package org.kainos.ea.api;

import org.kainos.ea.cli.CapabilityRequest;
import org.kainos.ea.cli.JobCapability;
import org.kainos.ea.client.FailedToAddJobCapabilityException;
import org.kainos.ea.client.FailedToGetJobCapabilityException;
import org.kainos.ea.client.JobCapabilityNotAddedException;
import org.kainos.ea.client.JobCapabilityNotFoundException;
import org.kainos.ea.db.JobCapabilityDao;

import java.sql.SQLException;
import java.util.List;

public class JobCapabilityService {
    private JobCapabilityDao jobCapabilityDao;

    public JobCapabilityService(JobCapabilityDao jobCapabilityDao) {
        this.jobCapabilityDao = jobCapabilityDao;
    }

    public JobCapability getCapabilityById(int id) throws FailedToGetJobCapabilityException,
            JobCapabilityNotFoundException {
        try {
            JobCapability jobCapability = jobCapabilityDao.getCapabilityById(id);

            if (jobCapability == null) {
                throw new JobCapabilityNotFoundException();
            }

            return jobCapability;
        } catch (SQLException e) {
            throw new FailedToGetJobCapabilityException();
        }
    }

    public List<JobCapability> getAllCapabilities() throws FailedToGetJobCapabilityException,
            JobCapabilityNotFoundException {
        try {
            List<JobCapability> jobCapabilities = jobCapabilityDao.getAllCapabilities();

            if (jobCapabilities.isEmpty()) {
                throw new JobCapabilityNotFoundException();
            }

            return jobCapabilities;
        } catch (SQLException e) {
            throw new FailedToGetJobCapabilityException();
        }
    }

    public int addCapability(CapabilityRequest capabilityRequest) throws JobCapabilityNotAddedException,
            FailedToAddJobCapabilityException {
        try {
            int capabilityId = jobCapabilityDao.addCapability(capabilityRequest);

            if (capabilityId == 0) {
                throw new JobCapabilityNotAddedException();
            }

            return capabilityId;
        } catch (SQLException e) {
            throw new FailedToAddJobCapabilityException();
        }
    }
}
