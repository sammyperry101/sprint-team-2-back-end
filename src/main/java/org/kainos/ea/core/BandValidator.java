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
    public String isValidBand(Band band) throws FailedToGetBandException{
        try{
            // Check band exists
            bandService.getBandById(band.getBandID());
            return null;
        } catch (BandDoesNotExistException e){
            return "Band does not exist";
        }
    }
}
