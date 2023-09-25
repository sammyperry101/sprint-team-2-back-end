package org.kainos.ea.api;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.kainos.ea.cli.JobRole;
import org.kainos.ea.cli.JobRoleRequest;
import org.kainos.ea.client.FailedToGetJobRole;
import org.kainos.ea.client.FailedToGetJobRoles;
import org.kainos.ea.client.JobRoleDoesNotExistException;
import org.kainos.ea.client.JobRolesNotFoundException;
import org.kainos.ea.db.JobRoleDao;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class JobRoleServiceTest {

    JobRoleDao jobRoleDaoMock = Mockito.mock(JobRoleDao.class);

    JobRoleService jobRoleService = new JobRoleService(jobRoleDaoMock);
    @Test
    void viewRoles_ShouldReturnRoles_WhenDaoReturnRoles() throws SQLException, JobRolesNotFoundException, FailedToGetJobRoles {
        JobRole jobRole = new JobRole(1,
                "Job Spec",
                "Responsibilities",
                "responsibilities",
                "sharepoint link",
                1,
                1);

        List<JobRole> expectedRoles = new ArrayList<>();
        expectedRoles.add(jobRole);

        Mockito.when(jobRoleDaoMock.getJobRoles()).thenReturn(expectedRoles);

        List<JobRole> resultRoles = jobRoleService.viewRoles();

        assertIterableEquals(resultRoles, expectedRoles);
    }

    @Test
    void viewRoles_ShouldThrowJobRolesNotFoundException_WhenRolesIsEmpty() throws SQLException {
        List<JobRole> expectedRoles = new ArrayList<>();

        Mockito.when(jobRoleDaoMock.getJobRoles()).thenReturn(expectedRoles);

        assertThrows(JobRolesNotFoundException.class, () -> jobRoleService.viewRoles());
    }

    @Test
    void viewRoles_ShouldThrowFailedToGetJobRoles_WhenSQLExceptionCaught() throws SQLException {
        Mockito.when(jobRoleDaoMock.getJobRoles()).thenThrow(SQLException.class);

        assertThrows(FailedToGetJobRoles.class, () -> jobRoleService.viewRoles());
    }

    @Test
    void editRole_ShouldEditJobRoleSuccessfully_WhenDaoReturnsSuccess() throws SQLException, JobRoleDoesNotExistException, FailedToGetJobRole {
        int id = 5;
        JobRoleRequest jobRoleRequest = new JobRoleRequest("NewName", "NewSpec", "NewResponsibilities", "NewLink", 2, 2);

        // Mock the jobRoleDao.editJobRole to return a success indicator, e.g., 1 for one row affected.
        Mockito.when(jobRoleDaoMock.editJobRole(id, jobRoleRequest)).thenReturn(5);

        // Mock the jobRoleDao to return an existing job role when getById is called.
        JobRole existingJobRole = new JobRole(5, "NewName", "NewSpec", "NewResponsibilities", "NewLink", 2, 2);
        Mockito.when(jobRoleDaoMock.getJobRoleById(id)).thenReturn(existingJobRole);

        // Call the editJobRole method in jobRoleService
        jobRoleService.editJobRole(id, jobRoleRequest);

        // Verify that the jobRoleDao.editJobRole was called with the correct parameters
        Mockito.verify(jobRoleDaoMock).editJobRole(id, jobRoleRequest);

    }

    @Test
    void ediRole_ShouldReturnId_WhenDaoReturnId() throws SQLException, JobRolesNotFoundException, FailedToGetJobRoles {
        JobRole jobRole = new JobRole(1,
                "Job Spec",
                "Responsibilities",
                "responsibilities",
                "sharepoint link",
                1,
                1);

        List<JobRole> expectedRoles = new ArrayList<>();
        expectedRoles.add(jobRole);

        Mockito.when(jobRoleDaoMock.getJobRoles()).thenReturn(expectedRoles);

        List<JobRole> resultRoles = jobRoleService.viewRoles();

        assertIterableEquals(resultRoles, expectedRoles);
    }

    @Test
    void editRole_ShouldThrowJobRoleDoesNotExistException_WhenSQLExceptionCaught() throws SQLException {
        int id = 5;
        JobRoleRequest jobRoleRequest = new JobRoleRequest("NewName", "NewSpec", "NewResponsibilities", "NewLink", 2, 2);

        Mockito.when(jobRoleDaoMock.editJobRole(id, jobRoleRequest)).thenThrow(SQLException.class);

        assertThrows(JobRoleDoesNotExistException.class, () -> jobRoleService.editJobRole(id, jobRoleRequest));
    }
}