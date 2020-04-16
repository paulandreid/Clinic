package com.Model;

import java.util.ArrayList;
import java.util.List;

public class Doctor {

    private String firstName;
    private String lastName;
    private int age;
    private Long identificationNumber;
    private int earnedShift;
    private int timeSpentConsulting;
    private int shiftTime;
    private List<Patient> patientsConsulted;

    public Doctor(String firstName, String lastName, int age, Long identificationNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.identificationNumber = identificationNumber;
        this.patientsConsulted = new ArrayList<Patient>();
        this.earnedShift = 0;
        this.timeSpentConsulting = 0;
        this.shiftTime = 0;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public int getTimeSpentConsulting() {
        return timeSpentConsulting;
    }

    public void setTimeSpentConsulting(int timeSpentConsulting) {
        this.timeSpentConsulting = timeSpentConsulting;
    }

    public void shiftTime(int timeSpentConsulting) {
        this.timeSpentConsulting = timeSpentConsulting;
    }

    public String getLastName() {
        return lastName;
    }

    public int getShiftTime() {
        return shiftTime;
    }

    public void setShiftTime(int shiftTime) {
        this.shiftTime = shiftTime;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Long getIdentificationNumber() {
        return identificationNumber;
    }

    public void setIdentificationNumber(Long identificationNumber) {
        this.identificationNumber = identificationNumber;
    }

    public boolean equals(Long identificationNumber) {
        return this.identificationNumber.equals(identificationNumber);
    }

    public List<Patient> getPatientsConsulted() {
        return this.patientsConsulted;
    }

    public int getEarnedShift() {
        return earnedShift;
    }

    public void setEarnedShift(int earnedShift) {
        this.earnedShift = earnedShift;
    }

    public void addPatient(com.Model.Patient patient) {
        this.patientsConsulted.add(patient);
    }

    @Override
    public String toString() {
        for (Patient patient : this.patientsConsulted
        ) {
            this.earnedShift += patient.getAppointment().getCost();
        }
        return firstName + ", " +
                lastName + " - " +
                identificationNumber +
                "; " + +this.patientsConsulted.size() + " patients" +
                ", " + this.timeSpentConsulting +
                " minutes , " + this.earnedShift +
                " RON ";
    }
}
