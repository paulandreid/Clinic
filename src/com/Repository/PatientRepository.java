package com.Repository;

import com.Model.Consultation;
import com.Model.Patient;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class PatientRepository {

    private List<Patient> patients;
    private File file;

    /**
     *
     */
    public PatientRepository() {
        this.patients = new ArrayList<Patient>();
        this.file = new File("files/patients");
    }

    /**
     * @param firstName
     * @param lastName
     * @param age
     * @param consultation
     * @return
     */
    public Patient addPatient(String firstName, String lastName, int age, Consultation consultation) {

        this.patients.add(new Patient(firstName, lastName, age, consultation));
        return null;
    }

    /**
     * @param firstName
     * @param lastName
     * @param age
     * @param consultation
     * @return
     */
    public Patient deletePatient(String firstName, String lastName, int age, Consultation consultation) {

        for (Patient patient : this.patients
        ) {
            if (patient.getFirstName().equals(firstName) && patient.getLastName().equals(lastName) && patient.getAge() == age && patient.getAppointment().name().equals(consultation.name())) {
                this.patients.remove(patient);
                return patient;
            }
        }

        return null;
    }

    /**
     *
     * @param firstName
     * @param lastName
     * @param age
     * @param consultation
     * @return
     */
    public Boolean findPerson(String firstName, String lastName, int age, Consultation consultation) {

        for (Patient patient : this.patients
        ) {
            if (patient.getFirstName().equals(firstName) && patient.getLastName().equals(lastName) && patient.getAge() == age && patient.getAppointment().name().equals(consultation.name())) {
                return true;
            }
        }

        return false;
    }

    /**
     *
     * @return
     */
    public List<Patient> findAll() {
        return this.patients;
    }

    /**
     * List<0> represents children, List<1> pupils, List<2> students, List<3> adults
     * @return a list of the number of patients in each age group
     */
    public List<Long> patientsSummary() {
        List<Long> summary = new ArrayList<Long>();
        summary.add(patients.stream()
                .filter(x -> x.getAge() < 2)
                .count());

        summary.add(patients.stream()
                .filter(x -> x.getAge() > 1 && x.getAge() < 8)
                .count());

        summary.add(patients.stream()
                .filter(x -> x.getAge() > 7 && x.getAge() < 19)
                .count());

        summary.add(patients.stream()
                .filter(x -> x.getAge() > 18)
                .count());
        return summary;
    }

    /**
     *
     * @return a list of patients that were not consulted
     */
    public List<String> patientsNotConsulted() {
        List<String> patientsNotConsulted = new ArrayList<String>();
        for (Patient patient : this.patients
        ) {
            if (!patient.getConsulted())
                patientsNotConsulted.add(patient.toString());
        }
        return patientsNotConsulted;
    }

    /**
     *
     * @throws FileNotFoundException
     */
    public void saveToFile() throws FileNotFoundException {
        PrintWriter output = new PrintWriter(file);
        for (Patient patient : this.findAll()
        ) {
            output.write(patient.toString() + "\n");
        }
        output.close();
    }
}

