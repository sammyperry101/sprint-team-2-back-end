package org.kainos.ea.cli;

public class Band {
    private int bandID;
    private String name;
    private int level;
    private String training_available;
    private String competencies;

    public Band(int bandID, String name, int level, String training_available, String competencies) {
        this.bandID = bandID;
        this.name = name;
        this.level = level;
        this.training_available = training_available;
        this.competencies = competencies;
    }

    public int getBandID() {
        return bandID;
    }

    public void setBandID(int bandID) {
        this.bandID = bandID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getTraining_available() {
        return training_available;
    }

    public void setTraining_available(String training_available) {
        this.training_available = training_available;
    }

    public String getCompetencies() {
        return competencies;
    }

    public void setCompetencies(String competencies) {
        this.competencies = competencies;
    }
}
