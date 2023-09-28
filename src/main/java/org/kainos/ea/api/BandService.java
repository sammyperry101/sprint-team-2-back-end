package org.kainos.ea.api;

import org.kainos.ea.cli.Band;
import org.kainos.ea.client.BandDoesNotExistException;
import org.kainos.ea.client.FailedToGetBandException;
import org.kainos.ea.client.FailedToGetBandsException;
import org.kainos.ea.db.BandDao;

import java.sql.SQLException;
import java.util.List;

public class BandService {
    BandDao bandDAO;

    public BandService(BandDao bandDAO) {
        this.bandDAO = bandDAO;
    }

    public List<Band> getBands() throws FailedToGetBandsException {
        try {
            return bandDAO.getBands();
        } catch (SQLException e){
            throw new FailedToGetBandsException();
        }
    }

    public Band getBandById(int id) throws FailedToGetBandException, BandDoesNotExistException {
        try {
            Band band = bandDAO.getBandById(id);
            if(band == null){
                throw new BandDoesNotExistException();
            }
            return bandDAO.getBandById(id);
        } catch (SQLException e){
            System.err.println(e.getMessage());
            throw new FailedToGetBandException();
        }
    }
}
