package org.kainos.ea.api;

import org.kainos.ea.cli.JobBand;
import org.kainos.ea.client.FailedToGetJobBandException;
import org.kainos.ea.client.JobBandNotFoundException;
import org.kainos.ea.db.JobBandDao;

import java.sql.SQLException;
import java.util.List;

public class JobBandService {
    private JobBandDao jobBandDao;
    public JobBandService(JobBandDao jobBandDao) {
        this.jobBandDao = jobBandDao;
    }
    public List<JobBand> getAllBands() throws FailedToGetJobBandException,
            JobBandNotFoundException {
        try {
            List<JobBand> jobBands = jobBandDao.getAllBands();

            if (jobBands.isEmpty()) {
                throw new JobBandNotFoundException();
            }

            return jobBands;
        } catch (SQLException e) {
            throw new FailedToGetJobBandException();
        }
    }
}
