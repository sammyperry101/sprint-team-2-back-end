package org.kainos.ea.validator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kainos.ea.cli.JobRoleRequest;
import org.kainos.ea.client.InvalidBandNameException;
import org.kainos.ea.client.InvalidCapabilityNameException;
import org.kainos.ea.client.InvalidSharepointLinkException;
import org.kainos.ea.client.InvalidNameException;
import org.kainos.ea.client.NullFieldException;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class JobRoleValidatorTest {

    private JobRoleValidator jobRoleValidator;
    private JobRoleRequest validJobRoleRequest;

    @BeforeEach
    public void setUp() {
        jobRoleValidator = new JobRoleValidator();
        validJobRoleRequest = new JobRoleRequest(1,
                "validName",
                "JobSpec",
                "responsibilities",
                "https://kainossoftwareltd.sharepoint.com/SitePages/Home.aspx",
                "Principal",
                "Workday"
        );
    }

    @Test
    public void isValidJobRole_NullObject_ThrowsException() {
        assertThrows(NullFieldException.class, () -> jobRoleValidator.validateJobRole(null));
    }

    @Test
    public void isValidJobRole_shouldReturnTrue_whenValidJobRole() {
        assertDoesNotThrow(() -> jobRoleValidator.validateJobRole(validJobRoleRequest));
    }

    @Test
    public void isValidJobRole_NullName_ThrowsException() {
        validJobRoleRequest.setRoleName(null);
        assertThrows(NullFieldException.class, () -> jobRoleValidator.validateJobRole(validJobRoleRequest));
    }

    @Test
    public void isValidJobRole_NullJobSpec_ThrowsException() {
        validJobRoleRequest.setJob_Spec(null);
        assertThrows(NullFieldException.class, () -> jobRoleValidator.validateJobRole(validJobRoleRequest));
    }

    @Test
    public void isValidJobRole_NullResponsibilities_ThrowsException() {
        validJobRoleRequest.setResponsibilities(null);
        assertThrows(NullFieldException.class, () -> jobRoleValidator.validateJobRole(validJobRoleRequest));
    }

    @Test
    public void isValidJobRole_NullSharePointLink_ThrowsException() {
        validJobRoleRequest.setSharepointLink(null);
        assertThrows(NullFieldException.class, () -> jobRoleValidator.validateJobRole(validJobRoleRequest));
    }

    @Test
    public void isValidJobRole_InvalidName_ThrowsException() {
        validJobRoleRequest.setRoleName("A Really LONG name A Really LONG name A Really LONG name A Really LONG name A Really "+
                "LONG name A Really LONG name A Really LONG name A Really LONG name A Really LONG name");
        assertThrows(InvalidNameException.class, () -> jobRoleValidator.validateJobRole(validJobRoleRequest));
    }

    @Test
    public void isValidJobRole_InvalidSharepointLink_ThrowsException() {
        validJobRoleRequest.setSharepointLink("invalidSharepointLink");
        assertThrows(InvalidSharepointLinkException.class, () -> jobRoleValidator.validateJobRole(validJobRoleRequest));
    }

    @Test
    public void isValidJobRole_InvalidBandName_ThrowsException() {
        validJobRoleRequest.setBandName("");
        assertThrows(InvalidBandNameException.class, () -> jobRoleValidator.validateJobRole(validJobRoleRequest));
    }

    @Test
    public void isValidJobRole_InvalidCapabilityName_ThrowsException() {
        validJobRoleRequest.setCapabilityName("");
        assertThrows(InvalidCapabilityNameException.class, () -> jobRoleValidator.validateJobRole(validJobRoleRequest));
    }

}
