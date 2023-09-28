package org.kainos.ea.validator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kainos.ea.cli.JobRoleEditRequest;
import org.kainos.ea.cli.JobRoleRequest;
import org.kainos.ea.client.InvalidBandIDException;
import org.kainos.ea.client.InvalidFamilyIDException;
import org.kainos.ea.client.InvalidSharepointLinkException;
import org.kainos.ea.client.InvalidNameException;
import org.kainos.ea.client.NullFieldException;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class JobRoleValidatorTest {

    private JobRoleValidator jobRoleValidator;
    private JobRoleRequest validJobRoleEditRequest;

    @BeforeEach
    public void setUp() {
        jobRoleValidator = new JobRoleValidator();
        validJobRoleEditRequest = new JobRoleRequest(1,
                "validName",
                "JobSpec",
                "responsibilities",
                "https://kainossoftwareltd.sharepoint.com/SitePages/Home.aspx",
                "band",
                "capability"
        );
    }

    @Test
    public void isValidJobRole_NullObject_ThrowsException() {
        assertThrows(NullFieldException.class, () -> jobRoleValidator.validateJobRole(null));
    }

    @Test
    public void isValidJobRole_shouldReturnTrue_whenValidJobRole() {
        assertDoesNotThrow(() -> jobRoleValidator.validateJobRole(validJobRoleEditRequest));
    }

    @Test
    public void isValidJobRole_NullName_ThrowsException() {
        validJobRoleEditRequest.setRoleName(null);
        assertThrows(NullFieldException.class, () -> jobRoleValidator.validateJobRole(validJobRoleEditRequest));
    }

    @Test
    public void isValidJobRole_NullJobSpec_ThrowsException() {
        validJobRoleEditRequest.setJob_Spec(null);
        assertThrows(NullFieldException.class, () -> jobRoleValidator.validateJobRole(validJobRoleEditRequest));
    }

    @Test
    public void isValidJobRole_NullResponsibilities_ThrowsException() {
        validJobRoleEditRequest.setResponsibilities(null);
        assertThrows(NullFieldException.class, () -> jobRoleValidator.validateJobRole(validJobRoleEditRequest));
    }

    @Test
    public void isValidJobRole_NullSharePointLink_ThrowsException() {
        validJobRoleEditRequest.setSharepointLink(null);
        assertThrows(NullFieldException.class, () -> jobRoleValidator.validateJobRole(validJobRoleEditRequest));
    }

    @Test
    public void isValidJobRole_InvalidName_ThrowsException() {
        validJobRoleEditRequest.setRoleName("A Really LONG name A Really LONG name A Really LONG name A Really LONG name A Really "+
                "LONG name A Really LONG name A Really LONG name A Really LONG name A Really LONG name");
        assertThrows(InvalidNameException.class, () -> jobRoleValidator.validateJobRole(validJobRoleEditRequest));
    }

    @Test
    public void isValidJobRole_InvalidSharepointLink_ThrowsException() {
        validJobRoleEditRequest.setSharepointLink("invalidSharepointLink");
        assertThrows(InvalidSharepointLinkException.class, () -> jobRoleValidator.validateJobRole(validJobRoleEditRequest));
    }

    @Test
    public void isValidJobRole_InvalidBandId_ThrowsException() {
        validJobRoleEditRequest.setBandName("");
        assertThrows(InvalidBandIDException.class, () -> jobRoleValidator.validateJobRole(validJobRoleEditRequest));
    }

    @Test
    public void isValidJobRole_InvalidFamilyId_ThrowsException() {
        validJobRoleEditRequest.setCapabilityName("");
        assertThrows(InvalidFamilyIDException.class, () -> jobRoleValidator.validateJobRole(validJobRoleEditRequest));
    }

}
