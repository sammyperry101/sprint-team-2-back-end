package org.kainos.ea.resources;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.kainos.ea.api.CapabilityService;
import org.kainos.ea.cli.CapabilityFamilyJoinRequest;
import org.kainos.ea.client.CapabilitiesWithFamilyIDNotFoundException;
import org.kainos.ea.client.FailedToGetCapabilitiesWithFamilyIDException;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
@ExtendWith(MockitoExtension.class)
public class CapabilityControllerTest {
    CapabilityService capabilityServiceMock = Mockito.mock(CapabilityService.class);

    CapabilityController capabilityController = new CapabilityController(capabilityServiceMock);

    @Test
    public void getCapabilitiesWFID_ShouldReturnResponse200_WhenServiceReturnsCapabilities() throws CapabilitiesWithFamilyIDNotFoundException, FailedToGetCapabilitiesWithFamilyIDException {
        List<CapabilityFamilyJoinRequest> expectedCapabilities = new ArrayList<>();

        Mockito.when(capabilityServiceMock.getCapabilitiesWithFamilyID()).thenReturn(expectedCapabilities);

        Response response = capabilityController.getCapabilitiesWithFamilyID();

        assertEquals(200, response.getStatus());
    }

    @Test
    public void getCapabilitiesWFID_ShouldReturnResponse500_WhenServiceThrowsFailedToGetException()
            throws CapabilitiesWithFamilyIDNotFoundException, FailedToGetCapabilitiesWithFamilyIDException {
        Mockito.when(capabilityServiceMock.getCapabilitiesWithFamilyID()).thenThrow(FailedToGetCapabilitiesWithFamilyIDException.class);

        Response response = capabilityController.getCapabilitiesWithFamilyID();

        assertEquals(500, response.getStatus());
    }

    @Test
    public void getCapabilitiesWFID_ShouldReturnResponse500_WhenServiceThrowsCouldNotFindException()
            throws CapabilitiesWithFamilyIDNotFoundException, FailedToGetCapabilitiesWithFamilyIDException {
        Mockito.when(capabilityServiceMock.getCapabilitiesWithFamilyID()).thenThrow(CapabilitiesWithFamilyIDNotFoundException.class);

        Response response = capabilityController.getCapabilitiesWithFamilyID();

        assertEquals(500, response.getStatus());
    }
}
