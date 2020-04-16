package com.Model;

import com.Model.Doctor;

import java.util.ArrayList;
import java.util.List;

public class Room {

    private Doctor doctor;
    private String name;
    private int shiftTimeRemaining; //in minutes
    private List<Doctor> doctorsAssigned;

    public Room(String name) {
        this.doctor = null;
        this.name = name;
        this.shiftTimeRemaining = 1320;  //time between 7-19
        this.doctorsAssigned = new ArrayList<Doctor>();
    }

    public int getShiftTimeRemaining() {
        return shiftTimeRemaining;
    }

    public void setShiftTimeRemaining(int shiftTimeRemaining) {
        this.shiftTimeRemaining = shiftTimeRemaining;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public String getName() {
        return name;
    }

    public List<Doctor> getDoctorsAssigned() {
        return doctorsAssigned;
    }

    public void setDoctorsAssigned(List<Doctor> doctorsAssigned) {
        this.doctorsAssigned = doctorsAssigned;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addDoctor(Doctor doctor) {
        this.doctorsAssigned.add(doctor);
    }
}
