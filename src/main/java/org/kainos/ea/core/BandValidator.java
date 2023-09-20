package org.kainos.ea.core;

import org.kainos.ea.cli.Band;
import org.kainos.ea.db.BandDAO;

public class BandValidator {
    BandDAO bandDAO;
    public BandValidator(BandDAO bandDAO){
        this.bandDAO = bandDAO;
    }
    //TODO Write this method to validate band
    public String isValidBand(Band band){
        bandDAO.getBandById(band.getBandID());
        return "do this later";
    }
}
