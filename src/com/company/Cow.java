package com.company;

import java.util.Date;

public class Cow extends Animal{


    private double weight;
    private final static String type = "c";

    public Cow(int tagNo, String gender, Date dateOfBirth, boolean purchased,double weight,String type) {
        super(tagNo, gender, dateOfBirth, purchased);
        this.weight = weight;
    }


    public static String getType() {
        return type;
    }


    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }
}
