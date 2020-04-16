package com.Model;

import com.Model.Consultation;

public class Patient {

    private String firstName;
    private String lastName;
    private int age;
    private Consultation appointment;
    private boolean consulted;

    public Patient(String firstName, String lastName, int age, Consultation appointment) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.appointment = appointment;
        this.consulted = false;
    }


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
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

    public Consultation getAppointment() {
        return appointment;
    }

    public void setAppointment(Consultation appointment) {
        this.appointment = appointment;
    }

    public boolean getConsulted() {
        return consulted;
    }

    public void setConsulted(boolean consulted) {
        this.consulted = consulted;
    }

    @Override
    public String toString() {
        return "Patient{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                ", appointment=" + appointment.name() +
                '}';
    }
}
