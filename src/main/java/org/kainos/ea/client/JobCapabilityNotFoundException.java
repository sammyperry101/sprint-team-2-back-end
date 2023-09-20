package org.kainos.ea.client;

public class JobCapabilityNotFoundException extends Throwable {
    @Override
    public String getMessage() {
        return "Job Capability not found";
    }
}
