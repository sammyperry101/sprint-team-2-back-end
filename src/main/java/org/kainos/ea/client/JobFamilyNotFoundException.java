package org.kainos.ea.client;

public class JobFamilyNotFoundException extends Throwable {
    @Override
    public String getMessage() {
        return "Job Family not found";
    }
}
