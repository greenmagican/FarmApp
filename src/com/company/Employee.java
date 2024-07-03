package com.company;

import java.util.Date;

public class Employee {

    private int empID;
    private String gender;
    private Date dateOfBirth;




    public Employee(int empID, String gender, Date dateOfBirth) {
        this.empID = empID;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;

    }

    public int getEmpID() {
        return empID;
    }

    public void setEmpID(int empID) {
        this.empID = empID;
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


}
