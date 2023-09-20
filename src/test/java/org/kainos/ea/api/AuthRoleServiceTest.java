package org.kainos.ea.api;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.kainos.ea.cli.AuthRole;
import org.kainos.ea.client.FailedToGetAuthRoles;
import org.kainos.ea.client.FailedToGetRoleException;
import org.kainos.ea.db.AuthRoleDao;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class AuthRoleServiceTest {
    private AuthRoleDao authRoleDaoMock = Mockito.mock(AuthRoleDao.class);

    private AuthRoleService authRoleService = new AuthRoleService(authRoleDaoMock);

    @Test
    void shouldReturnList() throws SQLException, FailedToGetAuthRoles {
        List<AuthRole> authRoles = new ArrayList<>();
        authRoles.add(new AuthRole(1, "Role1"));
        authRoles.add(new AuthRole(2, "Role2"));

        // Mock the behavior of AuthRoleDao
        Mockito.when(authRoleDaoMock.getAuthRoles()).thenReturn(authRoles);

        // Call the method under test
        List<AuthRole> result = authRoleService.getAuthRoles();

        // Verify the result
        assertEquals(authRoles, result);
    }

    @Test
    void testGetAuthRolesSQLException() throws SQLException {
        // Mock the behavior of AuthRoleDao to throw an SQLException
        Mockito.when(authRoleDaoMock.getAuthRoles()).thenThrow(new SQLException("Test SQL Exception"));

        // Call the method under test and expect a FailedToGetAuthRoles exception
        assertThrows(FailedToGetAuthRoles.class, () -> authRoleService.getAuthRoles());
    }

    @Test
    void testGetRoleById() throws FailedToGetRoleException, SQLException {
        // Create a mock AuthRole for testing
        AuthRole authRole = new AuthRole(1, "Role1");

        // Mock the behavior of AuthRoleDao
        Mockito.when(authRoleDaoMock.getRoleById(1)).thenReturn(authRole);

        // Call the method under test
        AuthRole result = authRoleService.getRoleById(1);

        // Verify the result
        assertEquals(authRole, result);
    }

    @Test
    void testGetRoleByIdSQLException() throws SQLException {
        // Mock the behavior of AuthRoleDao to throw an SQLException
        Mockito.when(authRoleDaoMock.getRoleById(1)).thenThrow(new SQLException("Test SQL Exception"));

        // Call the method under test and expect a FailedToGetRoleException
        assertThrows(FailedToGetRoleException.class, () -> authRoleService.getRoleById(1));
    }

}
