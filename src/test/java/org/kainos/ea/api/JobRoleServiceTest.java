package org.kainos.ea.api;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.kainos.ea.cli.JobRole;
import org.kainos.ea.cli.JobRoleRequest;
import org.kainos.ea.client.*;
import org.kainos.ea.core.BandValidator;
import org.kainos.ea.core.FamilyValidator;
import org.kainos.ea.core.JobRoleValidator;
import org.kainos.ea.db.JobRoleDao;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class JobRoleServiceTest {

    JobRoleDao jobRoleDaoMock = Mockito.mock(JobRoleDao.class);

    JobRoleService jobRoleService = new JobRoleService(jobRoleDaoMock);
    JobRoleValidator jobRoleValidatorMock = Mockito.mock(JobRoleValidator.class);
    BandValidator bandValidatorMock = Mockito.mock(BandValidator.class);
    FamilyValidator familyValidatorMock = Mockito.mock(FamilyValidator.class);

    @Test
    void viewRoles_ShouldReturnRoles_WhenDaoReturnRoles()
            throws SQLException, JobRolesNotFoundException, FailedToGetJobRoles
    {
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
    void createJobRole_shouldReturnId_whenJobRoleCreated()
            throws SQLException, InvalidJobRoleException, FailedToCreateJobRoleException
    {
        int expectedResult = 1;
        JobRoleRequest jobRoleRequest = new JobRoleRequest(
                "Name",
                "Specification",
                "Responsibilities",
                "sharepoint",
                1,
                1
        );

        Mockito.when(jobRoleDaoMock.createJobRole(jobRoleRequest)).thenReturn(expectedResult);

        int result = jobRoleService.createJobRole(jobRoleRequest);
        assertEquals(expectedResult, result);
    }

    @Test
    void createJobRole_shouldThrowFailedToCreateJobRoleException_whenDaoThrowsSQLException()
            throws SQLException
    {
        JobRoleRequest jobRoleRequest = new JobRoleRequest(
                "Name",
                "Specification",
                "Responsibilities",
                "sharepoint",
                1,
                1
        );


        Mockito.when(jobRoleDaoMock.createJobRole(jobRoleRequest)).thenThrow(SQLException.class);

        assertThrows(FailedToCreateJobRoleException.class, () -> jobRoleService.createJobRole(jobRoleRequest));
    }

    @Test
    void createJobRole_shouldThrowInvalidJobRoleException_whenJobRoleIsInvalid()
            throws SQLException, FailedToGetFamilyException, FailedToGetBandException {
        JobRoleRequest jobRoleRequest = new JobRoleRequest(
                "Name",
                "Specification",
                "Responsibilities",
                "sharepoint",
                1000,
                1000
        );

        Mockito.when(jobRoleValidatorMock.isValidJobRole(jobRoleRequest)).thenReturn("Band does not exist.");

        assertThrows(InvalidJobRoleException.class, () -> jobRoleService.createJobRole(jobRoleRequest));
    }
}