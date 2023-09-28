package org.kainos.ea.api;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.kainos.ea.client.FailedToGetJobRole;
import org.kainos.ea.cli.JobRoleRequest;
import org.kainos.ea.client.JobRoleDoesNotExistException;
import org.kainos.ea.client.FailedToDeleteJobRoleException;
import org.kainos.ea.client.FailedToGetJobRolesException;

import org.kainos.ea.cli.JobRoleFilter;

import org.kainos.ea.client.JobRolesNotFoundException;
import org.kainos.ea.db.JobRoleDao;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyInt;

@ExtendWith(MockitoExtension.class)
public class JobRoleServiceTest {

    JobRoleDao jobRoleDaoMock = Mockito.mock(JobRoleDao.class);

    JobRoleFilter filter = Mockito.mock(JobRoleFilter.class);

    JobRoleService jobRoleService = new JobRoleService(jobRoleDaoMock);

    @Test
    void deleteJobRole_shouldReturn1_whenDaoDeletesJobRole() throws JobRoleDoesNotExistException,
            FailedToDeleteJobRoleException, SQLException {
        int id = 1;
        int expectedResult = 1;

        JobRoleRequest jobRole = new JobRoleRequest(1,
                "Job Spec",
                "sharepoint link",
                "band name",
                "capability name",
                "Principal",
                "Workday"
        );

        Mockito.when(jobRoleDaoMock.getRoleById(id)).thenReturn(jobRole);
        Mockito.when(jobRoleDaoMock.deleteRole(id)).thenReturn(expectedResult);

        int result = jobRoleService.deleteRole(id);

        assertEquals(result, expectedResult);
    }

    @Test
    void deleteJobRole_shouldThrowJobDoesNotExistException_whenDaoReturnsNull() throws SQLException {
        int id = -1;
        int expectedResult = 0;

        Mockito.when(jobRoleDaoMock.getRoleById(id)).thenReturn(null);

        assertThrows(JobRoleDoesNotExistException.class, () -> jobRoleService.deleteRole(id));
    }

    @Test
    void deleteJobRole_shouldThrowFailedToDeleteJobRoleException_whenDaoThrowsSQLException() throws SQLException {
        int id = -1;

        Mockito.when(jobRoleDaoMock.getRoleById(id)).thenReturn(null);
        Mockito.when(jobRoleDaoMock.deleteRole(id)).thenThrow(SQLException.class);

        assertThrows(JobRoleDoesNotExistException.class, () -> jobRoleService.deleteRole(id));
    }

    @Test
    void viewRoles_ShouldReturnRoles_WhenDaoReturnRoles() throws SQLException, JobRolesNotFoundException,
            FailedToGetJobRolesException {
        JobRoleRequest expectedRole = new JobRoleRequest(1,
                "testname",
                "testlink",
                "testname",
                "testname",
                "Principal",
                "Workday");

        List<JobRoleRequest> expectedRoles = new ArrayList<>();
        expectedRoles.add(expectedRole);

        Mockito.when(jobRoleDaoMock.getJobRoles()).thenReturn(expectedRoles);

        List<JobRoleRequest> actualRoles = jobRoleService.viewRoles();

        assertIterableEquals(actualRoles, expectedRoles);
    }

    @Test
    void viewRoles_ShouldThrowJobRolesNotFoundException_WhenRolesIsEmpty() throws SQLException {
        List<JobRoleRequest> expectedRoles = new ArrayList<>();

        Mockito.when(jobRoleDaoMock.getJobRoles()).thenReturn(expectedRoles);

        assertThrows(JobRolesNotFoundException.class, () -> jobRoleService.viewRoles());
    }

    @Test
    void viewRoles_ShouldThrowFailedToGetJobRoles_WhenSQLExceptionCaught() throws SQLException {
        Mockito.when(jobRoleDaoMock.getJobRoles()).thenThrow(SQLException.class);

        assertThrows(FailedToGetJobRolesException.class, () -> jobRoleService.viewRoles());
    }

    @Test
    void viewRolesWithFilter_ShouldReturnRoles_WhenDaoReturnsRoles()
            throws SQLException, JobRolesNotFoundException, FailedToGetJobRolesException {
        JobRoleRequest expectedRole = new JobRoleRequest(1,
                "testname",
                "testlink",
                "testname",
                "testname",
                "Principal",
                "Workday");

        List<JobRoleRequest> expectedRoles = new ArrayList<>();
        expectedRoles.add(expectedRole);

        Mockito.when(jobRoleDaoMock.getJobRolesWithFilter(filter)).thenReturn(expectedRoles);

        List<JobRoleRequest> actualRoles = jobRoleService.viewRolesWithFilter(filter);

        assertIterableEquals(actualRoles, expectedRoles);
    }
    @Test
    void getJobRoleById_shouldReturnJobRole_whenDaoReturnsJobRole() throws JobRoleDoesNotExistException,
            SQLException, FailedToGetJobRole {
        int id = 1;
        JobRoleRequest expectedRole = new JobRoleRequest(1,
                "testname",
                "testlink",
                "testname",
                "testname",
                "Principal",
                "Workday");

        Mockito.when(jobRoleDaoMock.getRoleById(anyInt())).thenReturn(expectedRole);

        JobRoleRequest resultRole = jobRoleService.getRoleById(id);

        assertEquals(expectedRole, resultRole);
    }
    @Test
    void viewRolesWithFilter_ShouldThrowFailedToGetJobRoles_WhenSQLExceptionCaught() throws SQLException {
        Mockito.when(jobRoleDaoMock.getJobRolesWithFilter(filter)).thenThrow(SQLException.class);

        assertThrows(FailedToGetJobRolesException.class, () -> jobRoleService.viewRolesWithFilter(filter));
    }

    @Test
    void getJobRoleById_shouldThrowJobRoleDoesNotExist_whenDaoReturnsNull() throws SQLException {
        int id = -1;

        Mockito.when(jobRoleDaoMock.getRoleById(anyInt())).thenReturn(null);

        assertThrows(JobRoleDoesNotExistException.class, () -> jobRoleService.getRoleById(id));
    }

    @Test
    void getJobRoleById_shouldThrowFailedToDeleteJobRole_whenDaoThrowsSQLException() throws SQLException {
        int id = -1;

        Mockito.when(jobRoleDaoMock.getRoleById(anyInt())).thenThrow(SQLException.class);

        assertThrows(FailedToGetJobRole.class, () -> jobRoleService.getRoleById(id));
    }

    @Test
    void editRole_ShouldEditJobRoleSuccessfully_WhenDaoReturnsSuccess()
            throws SQLException, JobRoleDoesNotExistException, FailedToGetJobRole {
        int id = 5;
        JobRoleRequest updatedJobRole = new JobRoleRequest(5, "NewName", "NewSpec", "NewResponsibilities",
                "https://kainossoftwareltd.sharepoint.com/SitePages/Home.aspx", "Principal", "Workday");

        // Mock the jobRoleDao.editJobRole to return a success indicator, e.g., 1 for one row affected.
        Mockito.when(jobRoleDaoMock.editJobRole(id, updatedJobRole)).thenReturn(5);

        // Mock the jobRoleDao to return an existing job role when getById is called.
        JobRoleRequest existingJobRole = new JobRoleRequest(5, "NewName", "NewSpec", "NewResponsibilities",
                "https://kainossoftwareltd.sharepoint.com/SitePages/Home.aspx", "Principal", "Workday");
        Mockito.when(jobRoleDaoMock.getRoleById(id)).thenReturn(existingJobRole);

        // Call the editJobRole method in jobRoleService
        jobRoleService.editJobRole(id, updatedJobRole);

        // Verify that the jobRoleDao.editJobRole was called with the correct parameters
        Mockito.verify(jobRoleDaoMock).editJobRole(id, updatedJobRole);

    }

    @Test
    void editRole_ShouldThrowJobRoleDoesNotExistException_WhenSQLExceptionCaught() throws SQLException {
        int id = 5;
        JobRoleRequest jobRoleRequest = new JobRoleRequest(1,"NewName", "NewSpec", "NewResponsibilities"
                , "https://kainossoftwareltd.sharepoint.com/SitePages/Home.aspx", "Principal", "Workday");

        Mockito.when(jobRoleDaoMock.editJobRole(id, jobRoleRequest)).thenThrow(SQLException.class);

        assertThrows(JobRoleDoesNotExistException.class, () -> jobRoleService.editJobRole(id, jobRoleRequest));
    }
}