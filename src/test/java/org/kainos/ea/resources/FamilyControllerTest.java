package org.kainos.ea.resources;

import org.junit.jupiter.api.Test;
import org.kainos.ea.api.FamilyService;
import org.kainos.ea.cli.Family;
import org.kainos.ea.client.FailedToGetFamilyException;
import org.kainos.ea.client.FamilyDoesNotExistException;
import org.mockito.Mockito;

import javax.ws.rs.core.Response;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class FamilyControllerTest {
    FamilyService familyService = Mockito.mock(FamilyService.class);
    Family familyMock = Mockito.mock(Family.class);
    FamilyController familyController = new FamilyController(familyService);

    @Test
    void getFamilyById_shouldReturnResponse200_whenValidId()
            throws FailedToGetFamilyException, FamilyDoesNotExistException
    {
        Mockito.when(familyService.getFamilyById(1)).thenReturn(familyMock);
        Response response = familyController.getFamilyById(1);
        assertEquals(200, response.getStatus());
        assertNotNull(response.getEntity());
    }

    @Test
    void getFamilyById_shouldReturnResponse400_whenFamilyDoesNOTExist()
            throws FailedToGetFamilyException, FamilyDoesNotExistException
    {
        Mockito.when(familyService.getFamilyById(1)).thenThrow(FamilyDoesNotExistException.class);
        Response response = familyController.getFamilyById(1);
        assertEquals(400, response.getStatus());
    }

    @Test
    void getFamilyById_shouldReturnResponse500_whenFailedToGetFamily()
            throws FailedToGetFamilyException, FamilyDoesNotExistException
    {
        Mockito.when(familyService.getFamilyById(1)).thenThrow(FailedToGetFamilyException.class);
        Response response = familyController.getFamilyById(1);
        assertEquals(500, response.getStatus());
    }
}
