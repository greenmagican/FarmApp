package com.company;

import java.util.Date;

public class Sheep extends Animal{


    private final static String type = "s";



    public Sheep(int tagNo, String gender, Date dateOfBirth, boolean purchased,String type) {

        super(tagNo, gender, dateOfBirth, purchased);
    }

    public static String getType() {
        return type;
    }











}
