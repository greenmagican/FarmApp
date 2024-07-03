package com.company;

import java.util.Date;

public class Veterinary extends Employee {


    private boolean BScDegree;
    private Date dateOfGraduation;
    private int expertiseLevel;


    public Veterinary(int empID, String gender, Date dateOfBirth, boolean BScDegree, Date dateOfGraduation, int expertiseLevel) {
        super(empID, gender, dateOfBirth);
        this.BScDegree = BScDegree;
        this.dateOfGraduation = dateOfGraduation;
        this.expertiseLevel = expertiseLevel;
    }


    public boolean isBScDegree() {
        return BScDegree;
    }

    public void setBScDegree(boolean BScDegree) {
        this.BScDegree = BScDegree;
    }

    public Date getDateOfGraduation() {
        return dateOfGraduation;
    }

    public void setDateOfGraduation(Date dateOfGraduation) {
        this.dateOfGraduation = dateOfGraduation;
    }

    public int getExpertiseLevel() {
        return expertiseLevel;
    }

    public void setExpertiseLevel(int expertiseLevel) {
        this.expertiseLevel = expertiseLevel;
    }
}
