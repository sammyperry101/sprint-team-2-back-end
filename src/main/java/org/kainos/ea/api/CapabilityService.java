package org.kainos.ea.api;

import org.kainos.ea.cli.CapabilityFamilyJoinRequest;
import org.kainos.ea.client.CapabilitiesWithFamilyIDNotFoundException;
import org.kainos.ea.client.FailedToGetCapabilitiesWithFamilyIDException;
import org.kainos.ea.db.CapabilityDao;

import java.sql.SQLException;
import java.util.List;

public class CapabilityService {

    CapabilityDao capabilityDao;

    public CapabilityService(CapabilityDao capabilityDao) {
        this.capabilityDao = capabilityDao;
    }

    public List<CapabilityFamilyJoinRequest> getCapabilitiesWithFamilyID() throws FailedToGetCapabilitiesWithFamilyIDException, CapabilitiesWithFamilyIDNotFoundException {
        try{
            List<CapabilityFamilyJoinRequest> list = capabilityDao.getCapabilitiesWithFamilyID();

            if(list.isEmpty()){
                throw new CapabilitiesWithFamilyIDNotFoundException();
            }

            return list;
        } catch (SQLException e) {
            System.err.println(e.getMessage());

            throw new FailedToGetCapabilitiesWithFamilyIDException();
        }
    }
}
