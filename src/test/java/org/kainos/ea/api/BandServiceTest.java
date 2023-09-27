package org.kainos.ea.api;

import org.junit.jupiter.api.Test;
import org.kainos.ea.cli.Band;
import org.kainos.ea.client.BandDoesNotExistException;
import org.kainos.ea.client.FailedToGetBandException;
import org.kainos.ea.db.BandDao;
import org.mockito.Mockito;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class BandServiceTest {
    BandDao bandDaoMock = Mockito.mock(BandDao.class);
    BandService bandService = new BandService(bandDaoMock);

    @Test
    void getBandById_shouldReturnBand_whenIdIsValid()
            throws SQLException, FailedToGetBandException, BandDoesNotExistException {
        Band expectedBand =
                new Band(1, "Band", 1, "Trains", "Competent");
        Mockito.when(bandDaoMock.getBandById(1)).thenReturn(expectedBand);

        Band result = bandService.getBandById(1);

        assertEquals(expectedBand, result);
    }

    @Test
    void getBandById_shouldThrowBandDoesNotExistException_whenDaoReturnsNull()
            throws SQLException
    {
        Mockito.when(bandDaoMock.getBandById(1000)).thenReturn(null);

        assertThrows(BandDoesNotExistException.class, () -> bandService.getBandById(1000));
    }

    @Test
    void getBandById_shouldThrowFailedToGetBandException_whenDaoThrowsSQLException()
        throws SQLException
    {
        Mockito.when(bandDaoMock.getBandById(1)).thenThrow(SQLException.class);

        assertThrows(FailedToGetBandException.class, () -> bandService.getBandById(1));
    }
}
