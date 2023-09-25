package org.kainos.ea.core;

import org.kainos.ea.api.BandService;
import org.kainos.ea.cli.Band;
import org.kainos.ea.client.BandDoesNotExistException;
import org.kainos.ea.client.FailedToGetBandException;

public class BandValidator {
    BandService bandService;
    public BandValidator(BandService bandService){
        this.bandService = bandService;
    }

    public boolean bandExists(int bandId)
            throws FailedToGetBandException
    {
        try{
            return bandService.getBandById(bandId) != null;
        } catch (BandDoesNotExistException e){
            return false;
        }
    }
}
