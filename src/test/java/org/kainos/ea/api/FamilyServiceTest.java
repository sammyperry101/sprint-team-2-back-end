package org.kainos.ea.api;

import org.junit.jupiter.api.Test;
import org.kainos.ea.cli.Family;
import org.kainos.ea.client.FailedToGetFamilyException;
import org.kainos.ea.client.FamilyDoesNotExistException;
import org.kainos.ea.db.FamilyDao;
import org.mockito.Mockito;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class FamilyServiceTest {
    FamilyDao familyDaoMock = Mockito.mock(FamilyDao.class);
    FamilyService familyService = new FamilyService(familyDaoMock);
    @Test
    void getFamilyById_shouldReturnFamily_whenIdIsValid()
            throws SQLException, FailedToGetFamilyException, FamilyDoesNotExistException
    {
        Family expectedFamily = new Family(1, "Family", 1);

        Mockito.when(familyDaoMock.getFamilyById(1)).thenReturn(expectedFamily);

        Family result = familyService.getFamilyById(1);

        assertEquals(expectedFamily, result);
    }

    @Test
    void getFamilyById_shouldThrowFamilyDoesNotExist_whenDaoReturnsNull() throws SQLException {
        Mockito.when(familyDaoMock.getFamilyById(1000)).thenReturn(null);

        assertThrows(FamilyDoesNotExistException.class, () -> familyService.getFamilyById(1000));
    }

    @Test
    void getFamilyById_shouldThrowFailedToGetFamilyException_whenDaoThrowsSQLException() throws SQLException {
        Mockito.when(familyDaoMock.getFamilyById(1)).thenThrow(SQLException.class);

        assertThrows(FailedToGetFamilyException.class, () -> familyService.getFamilyById(1));
    }
}
