package com.company;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

public abstract class Animal implements Serializable {

    private int tagNo;
    private String gender;
    private Date dateOfBirth;
    private boolean purchased;


    private HashMap<Date, Double> milking = new HashMap<Date, Double>();



    public Animal(int tagNo, String gender, Date dateOfBirth, boolean purchased) {
        this.tagNo = tagNo;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
        this.purchased = purchased;


    }





    public int getTagNo() {
        return tagNo;
    }

    public void setTagNo(int tagNo) {
        this.tagNo = tagNo;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public boolean isPurchased() {
        return purchased;
    }

    public void setPurchased(boolean purchased) {
        this.purchased = purchased;
    }

    public HashMap<Date, Double> getMilking() {
        return milking;
    }

    public void setMilking(HashMap<Date, Double> milking) {
        this.milking = milking;
    }




    public int getAge(Date birthDate){

        int years = 0;

        Calendar birthDay = Calendar.getInstance();
        birthDay.setTimeInMillis(birthDate.getTime());


        long currentTime = System.currentTimeMillis();
        Calendar now = Calendar.getInstance();
        now.setTimeInMillis(currentTime);


        years = now.get(Calendar.YEAR) - birthDay.get(Calendar.YEAR);

        return years;

    }

    @Override
    public String toString() {
        return "Animal{" +
                "tagNo=" + tagNo +
                ", gender='" + gender + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", purchased=" + purchased +
                '}';
    }

    //public abstract void feeding(Animal animalOfCow);   // abstract class feeding cause feeding method used different for each subclasses.
}
