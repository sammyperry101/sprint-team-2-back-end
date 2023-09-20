package org.kainos.ea.api;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.kainos.ea.cli.CapabilityFamilyJoinRequest;
import org.kainos.ea.client.CapabilitiesWithFamilyIDNotFoundException;
import org.kainos.ea.client.FailedToGetCapabilitiesWithFamilyIDException;
import org.kainos.ea.db.CapabilityDao;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class CapabilityServiceTest {
    CapabilityDao capabilityDaoMock = Mockito.mock(CapabilityDao.class);

    CapabilityService capabilityService = new CapabilityService(capabilityDaoMock);

    @Test
    public void getCapabilitiesWFID_ShouldReturnCapabilitiesWFID() throws SQLException, CapabilitiesWithFamilyIDNotFoundException, FailedToGetCapabilitiesWithFamilyIDException {
        CapabilityFamilyJoinRequest expectedCapability = new CapabilityFamilyJoinRequest(
                1,
                1,
                "testname"
        );

        List<CapabilityFamilyJoinRequest> expectedCapabilities = new ArrayList<>();

        expectedCapabilities.add(expectedCapability);

        Mockito.when(capabilityDaoMock.getCapabilitiesWithFamilyID()).thenReturn(expectedCapabilities);

        List<CapabilityFamilyJoinRequest> actualCapabilities = capabilityService.getCapabilitiesWithFamilyID();

        assertIterableEquals(expectedCapabilities, actualCapabilities);
    }

    @Test
    public void getCapabilitiesWFID_ShouldThrowCapabilitiesWithFamilyIDNotFoundException_WhenCapabilitiesISEmpty()
            throws SQLException {
        List<CapabilityFamilyJoinRequest> expectedCapabilities = new ArrayList<>();

        Mockito.when(capabilityDaoMock.getCapabilitiesWithFamilyID()).thenReturn(expectedCapabilities);

        assertThrows(CapabilitiesWithFamilyIDNotFoundException.class, () -> capabilityService.getCapabilitiesWithFamilyID());
    }

    @Test
    public void getCapabilitiesWFID_ShouldThrowFailedToGetCapabilitiesWithFamilyIDException_WhenSQLExceptionCaught()
            throws SQLException {
        List<CapabilityFamilyJoinRequest> expectedCapabilities = new ArrayList<>();

        Mockito.when(capabilityDaoMock.getCapabilitiesWithFamilyID()).thenThrow(SQLException.class);

        assertThrows(FailedToGetCapabilitiesWithFamilyIDException.class, () -> capabilityService.getCapabilitiesWithFamilyID());
    }
}