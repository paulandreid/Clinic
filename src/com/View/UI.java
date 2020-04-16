package com.View;

import com.View.ViewGenerating;

import java.util.Scanner;

public class UI {

    private ViewGenerating viewGenerating;

    public UI() {
        this.viewGenerating = new ViewGenerating();
    }

    /**
     * displays the IU in console and waits for keyboard input
     */
    public void displayUI() {
        while (true) {
            Scanner keyboard = new Scanner(System.in);

            System.out.println("\n-Clinic-\n" +
                    "a. Generate a list of doctors\n" +
                    "b. Generate a list of patients\n" +
                    "c1. Print the list of patients\n" +
                    "c2. Print the list of doctors\n" +
                    "c3. Print a summary of all patients\n" +
                    "d. Save the list of patients in a file\n" +
                    "e. Save the list of doctors in a file\n" +
                    "f1. Print a summary of the doctors, the number of patients consulted and the total amount billed\n" +
                    "f2. Print patient which were not consulted\n" +
                    "0. Exit\n");

            String op = keyboard.next();
            if (op.equals("0")) {
                break;
            }

            if (op.equals("a")) {
                viewGenerating.generateDoctor();
            }
            if (op.equals("b"))
                viewGenerating.generatePatient();
            if (op.equals("c1"))
                viewGenerating.printAllPatients();
            if (op.equals("c2"))
                viewGenerating.printAllDoctors();
            if (op.equals("c3"))
                viewGenerating.printPatientsSummary();
            if (op.equals("d"))
                viewGenerating.savePatientsToFile();
            if (op.equals("e"))
                viewGenerating.saveDoctorToFile();
            if (op.equals("f1"))
                viewGenerating.printDoctorsSummary();
            if (op.equals("f2"))
                viewGenerating.printPatientsNotConsulted();

        }
    }
}
