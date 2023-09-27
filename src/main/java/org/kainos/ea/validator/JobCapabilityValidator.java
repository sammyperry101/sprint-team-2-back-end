package org.kainos.ea.validator;

import org.kainos.ea.cli.CapabilityRequest;
import org.kainos.ea.client.CapabilityNameTooLongException;

public class JobCapabilityValidator {
    public boolean isValidCapability(CapabilityRequest capabilityRequest) throws CapabilityNameTooLongException {
        if (capabilityRequest.getName().length() > 70) {
            throw new CapabilityNameTooLongException();
        }

        return true;
    }
}
