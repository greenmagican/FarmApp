package com.company;

import java.util.Date;

public class FarmWorker extends Employee {

    private String previousFarmName;
    private int workExperience;


    public FarmWorker(int empID, String gender, Date dateOfBirth, String previousFarmName, int workExperience) {
        super(empID, gender, dateOfBirth);
        this.previousFarmName = previousFarmName;
        this.workExperience = workExperience;
    }


    public String getPreviousFarmName() {
        return previousFarmName;
    }

    public void setPreviousFarmName(String previousFarmName) {
        this.previousFarmName = previousFarmName;
    }

    public int getWorkExperience() {
        return workExperience;
    }

    public void setWorkExperience(int workExperience) {
        this.workExperience = workExperience;
    }
}
