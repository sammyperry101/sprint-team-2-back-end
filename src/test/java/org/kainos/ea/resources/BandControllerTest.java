package org.kainos.ea.resources;

import org.junit.jupiter.api.Test;
import org.kainos.ea.api.BandService;
import org.kainos.ea.cli.Band;
import org.kainos.ea.client.BandDoesNotExistException;
import org.kainos.ea.client.FailedToGetBandException;
import org.mockito.Mockito;

import javax.ws.rs.core.Response;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class BandControllerTest {
    BandService bandService = Mockito.mock(BandService.class);
    Band bandMock = Mockito.mock(Band.class);
    BandController bandController = new BandController(bandService);

    @Test
    void getBandById_shouldReturnResponse200_whenValidId()
            throws BandDoesNotExistException, FailedToGetBandException
    {
        Mockito.when(bandService.getBandById(1)).thenReturn(bandMock);
        Response response = bandController.getBandById(1);
        assertEquals(200, response.getStatus());
        assertNotNull(response.getEntity());
    }

    @Test
    void getBandById_shouldReturnResponse400_whenBandDoesNotExist()
            throws FailedToGetBandException, BandDoesNotExistException
    {
        Mockito.when(bandService.getBandById(1)).thenThrow(BandDoesNotExistException.class);
        Response response = bandController.getBandById(1);
        assertEquals(400, response.getStatus());
    }

    @Test
    void getBandById_shouldReturnResponse500_whenFailedToGetBand()
            throws FailedToGetBandException, BandDoesNotExistException
    {
        Mockito.when(bandService.getBandById(1)).thenThrow(FailedToGetBandException.class);
        Response response = bandController.getBandById(1);
        assertEquals(500, response.getStatus());
    }
}
