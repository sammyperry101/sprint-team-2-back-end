package org.kainos.ea.validator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kainos.ea.cli.JobRoleEditRequest;
import org.kainos.ea.client.InvalidBandIDException;
import org.kainos.ea.client.InvalidFamilyIDException;
import org.kainos.ea.client.InvalidSharepointLinkException;
import org.kainos.ea.client.InvalidNameException;
import org.kainos.ea.client.NullfieldException;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class JobRoleValidatorTest {

    private JobRoleValidator jobRoleValidator;
    private JobRoleEditRequest validJobRoleEditRequest;

    @BeforeEach
    public void setUp() {
        jobRoleValidator = new JobRoleValidator();
        validJobRoleEditRequest = new JobRoleEditRequest(
                "validName",
                "JobSpec",
                "responsibilities",
                "https://kainossoftwareltd.sharepoint.com/SitePages/Home.aspx",
                1,
                1
        );
    }

    @Test
    public void isValidJobRole_NullObject_ThrowsException() {
        assertThrows(NullfieldException.class, () -> jobRoleValidator.validateJobRole(null));
    }

    @Test
    public void isValidJobRole_shouldReturnTrue_whenValidJobRole() {
        assertDoesNotThrow(() -> jobRoleValidator.validateJobRole(validJobRoleEditRequest));
    }

    @Test
    public void isValidJobRole_NullName_ThrowsException() {
        validJobRoleEditRequest.setName(null);
        assertThrows(NullfieldException.class, () -> jobRoleValidator.validateJobRole(validJobRoleEditRequest));
    }

    @Test
    public void isValidJobRole_NullJobSpec_ThrowsException() {
        validJobRoleEditRequest.setJob_Spec(null);
        assertThrows(NullfieldException.class, () -> jobRoleValidator.validateJobRole(validJobRoleEditRequest));
    }

    @Test
    public void isValidJobRole_NullResponsibilities_ThrowsException() {
        validJobRoleEditRequest.setResponsibilities(null);
        assertThrows(NullfieldException.class, () -> jobRoleValidator.validateJobRole(validJobRoleEditRequest));
    }

    @Test
    public void isValidJobRole_NullSharePointLink_ThrowsException() {
        validJobRoleEditRequest.setSharepointLink(null);
        assertThrows(NullfieldException.class, () -> jobRoleValidator.validateJobRole(validJobRoleEditRequest));
    }

    @Test
    public void isValidJobRole_InvalidName_ThrowsException() {
        validJobRoleEditRequest.setName("A Really LONG name A Really LONG name A Really LONG name A Really LONG name A Really "+
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
        validJobRoleEditRequest.setBandId(-1);
        assertThrows(InvalidBandIDException.class, () -> jobRoleValidator.validateJobRole(validJobRoleEditRequest));
    }

    @Test
    public void isValidJobRole_InvalidFamilyId_ThrowsException() {
        validJobRoleEditRequest.setFamilyId(0);
        assertThrows(InvalidFamilyIDException.class, () -> jobRoleValidator.validateJobRole(validJobRoleEditRequest));
    }

}
