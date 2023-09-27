package org.kainos.ea.validator;

import org.junit.jupiter.api.Test;
import org.kainos.ea.cli.CapabilityRequest;
import org.kainos.ea.client.CapabilityNameTooLongException;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class JobCapabilityValidatorTest {
    JobCapabilityValidator jobCapabilityValidator = new JobCapabilityValidator();

    @Test
    public void isValidCapability_shouldReturnTrue_whenValidCapability() throws CapabilityNameTooLongException {
        CapabilityRequest capabilityRequest = new CapabilityRequest("valid capability");

        assertTrue(jobCapabilityValidator.isValidCapability(capabilityRequest));
    }

    @Test
    public void isValidCapability_shouldReturnFalse_whenInvalidCapability() throws CapabilityNameTooLongException {
        CapabilityRequest capabilityRequest =
                new CapabilityRequest("invalid capabilityinvalid capabilityinvalid capabilityinvalid capabilityinvalid");

        assertThrows(CapabilityNameTooLongException.class, () -> jobCapabilityValidator.isValidCapability(capabilityRequest));
    }
}
