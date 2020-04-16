package com.View;

import com.Controller.Controller;
import com.Model.Consultation;

import java.util.List;
import java.util.Random;

public class ViewGenerating {

    private Controller controller;

    public ViewGenerating() {
        controller = new Controller();
    }

    /**
     * @param leftLimit          left limit of possible strings to choose randomly
     * @param rightLimit         right limit of possible strings to choose randomly
     * @param targetStringLength size of generated string
     * @return a randomly generated string of length targetStringLength
     */
    private String generateString(int leftLimit, int rightLimit, int targetStringLength) {

        Random random = new Random();

        return random.ints(leftLimit, rightLimit + 1)
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }

    /**
     * generates a random list of doctors
     */
    public void generateDoctor() {
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        int firstNameLength = 3;
        int lastNameLength = 2;

        for (int i = 0; i < 20; i++) {
            while (true)
                try {
                    controller.addDoctor(generateString(leftLimit, rightLimit, firstNameLength)
                            , generateString(leftLimit, rightLimit, lastNameLength)
                            , Integer.parseInt(generateString(48, 57, 2))
                            , Long.parseLong(generateString(48, 57, 4)));
                    break;
                } catch (Exception ex) {
                    //System.out.println(ex.getMessage()); //in case of bad input, uncomment if necessary for console printing
                }
        }
    }

    public void generatePatient() {
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        int firstNameLength = 5;
        int lastNameLength = 4;

        controller.addPatient(generateString(leftLimit, rightLimit, firstNameLength) //to ensure that there exist at least one Children as patient
                , generateString(leftLimit, rightLimit, lastNameLength)              //0-1 age bracket is the least likely to be generated
                , new Random().nextInt(1 - 0 + 1)
                , Consultation.getRandomConsultation().name());

        for (int i = 0; i < 99; i++) {
            while (true)
                try {
                    controller.addPatient(generateString(leftLimit, rightLimit, firstNameLength)
                            , generateString(leftLimit, rightLimit, lastNameLength)
                            , new Random().nextInt(85 - 0 + 1)
                            , Consultation.getRandomConsultation().name());
                    break;
                } catch (Exception ex) {
                    //System.out.println(ex.getMessage()); //in case of bad input, uncomment if necessary for console printing
                }
        }
    }

    /**
     *
     */
    public void printAllPatients() {
        for (String patient : this.controller.printAllPatients()
        ) {
            System.out.println(patient);
        }
    }

    /**
     *
     */
    public void printAllDoctors() {
        for (String doctor : this.controller.printAllDoctors()
        ) {
            System.out.println(doctor);
        }
    }

    /**
     *
     */
    public void printPatientsSummary() {
        List<Long> summary = this.controller.patientsSummary();
        System.out.println("Children (0-1):" + summary.get(0) + " patients");
        System.out.println("Pupil (1-7):" + summary.get(1) + " patients");
        System.out.println("Student (7-18):" + summary.get(2) + " patients");
        System.out.println("Adults (>18):" + summary.get(3) + " patients");
    }

    /**
     *
     */
    public void printDoctorsSummary() {
        List<String> doctorsList = this.controller.doctorsSummary();
        for (String doctor : this.controller.doctorsSummary()
        ) {
            System.out.println(doctor);
        }
    }

    /**
     *
     */
    public void printPatientsNotConsulted() {
        for (String patient : this.controller.printPatientsNotConsulted()
        ) {
            System.out.println(patient);
        }
    }

    public void saveDoctorToFile(){
        this.controller.saveDoctorsToFile();
    }

    public void savePatientsToFile(){
        this.controller.savePateintsToFile();
    }
}
