package org.kainos.ea.api;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.kainos.ea.cli.Family;
import org.kainos.ea.client.FailedToGetFamilyException;
import org.kainos.ea.client.FamilyDoesNotExistException;
import org.kainos.ea.db.FamilyDao;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class FamilyServiceTest {
    FamilyDao familyDaoMock = Mockito.mock(FamilyDao.class);
    FamilyService familyService= new FamilyService(familyDaoMock);

    @Test
    public void getFamilyByID_ShouldReturnFamily_WhenDaoReturnsFamily() throws FailedToGetFamilyException, FamilyDoesNotExistException, SQLException {
        int testID = 1;

        Family expectedFamily = new Family(
                1,
                "testfamily",
                1
        );

        Mockito.when(familyDaoMock.getFamilyByID(testID)).thenReturn(expectedFamily);

        Family actualFamily = familyService.getFamilyByID(testID);

        assertEquals(expectedFamily.getFamilyID(), actualFamily.getFamilyID());
        assertEquals(expectedFamily.getName(), actualFamily.getName());
        assertEquals(expectedFamily.getCapabilityId(), actualFamily.getCapabilityId());
    }

    @Test
    public void getFamilyByID_ShouldThrowFamilyDoesNotExistException_WhenDaoReturnsNull() throws SQLException, FailedToGetFamilyException, FamilyDoesNotExistException {
        int testID = 1;

        Mockito.when(familyDaoMock.getFamilyByID(testID)).thenReturn(null);

        assertThrows(FamilyDoesNotExistException.class, () -> familyService.getFamilyByID(testID));
    }

    @Test
    public void getFamilyByID_ShouldThrowFailedToGetFamilyException_WhenDaoThrowsSQLException() throws SQLException {
        int testID = 1;

        Mockito.when(familyDaoMock.getFamilyByID(testID)).thenThrow(SQLException.class);

        assertThrows(FailedToGetFamilyException.class, () -> familyService.getFamilyByID(testID));
    }
}
