package org.kainos.ea.resources;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.kainos.ea.api.FamilyService;
import org.kainos.ea.cli.Family;
import org.kainos.ea.client.FailedToGetFamilyException;
import org.kainos.ea.client.FamilyDoesNotExistException;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.ws.rs.core.Response;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class FamilyControllerTest {
    FamilyService familyServiceMock = Mockito.mock(FamilyService.class);
    FamilyController familyController = new FamilyController(familyServiceMock);

    @Test
    public void getFamilyByID_ShouldReturnResponse200_WhenServiceReturnsFamily() throws FailedToGetFamilyException, FamilyDoesNotExistException {
        int testID = 1;

        Family expectedFamily = new Family(
                1,
                "testfamily",
                1
        );

        Mockito.when(familyServiceMock.getFamilyByID(testID)).thenReturn(expectedFamily);

        Response response = familyController.getFamilyByID(testID);

        assertEquals(200, response.getStatus());
    }

    @Test
    public void getFamilyByID_ShouldReturnResponse400_WhenServiceThrowsFamilyDoesNotExistException() throws FailedToGetFamilyException, FamilyDoesNotExistException {
        int testID = 1;

        Mockito.when(familyServiceMock.getFamilyByID(testID)).thenThrow(FamilyDoesNotExistException.class);

        Response response = familyController.getFamilyByID(testID);

        assertEquals(400, response.getStatus());
    }

    @Test
    public void getFamilyByID_ShouldReturnResponse500_WhenServiceThrowsFailedToGetFamilyException() throws FailedToGetFamilyException, FamilyDoesNotExistException {
        int testID = 1;

        Mockito.when(familyServiceMock.getFamilyByID(testID)).thenThrow(FailedToGetFamilyException.class);

        Response response = familyController.getFamilyByID(testID);

        assertEquals(500, response.getStatus());
    }
}
