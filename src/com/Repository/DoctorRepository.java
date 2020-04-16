package com.Repository;

import com.Model.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class DoctorRepository {

    private List<Doctor> doctors;
    private List<Room> rooms;
    private List<Consultation> consultations;
    private File file;


    public DoctorRepository() {
        file = new File("files/doctors");
        rooms = new ArrayList<Room>();
        for (int i = 1; i <= 4; i++)
            rooms.add(new Room(Integer.toString(i)));

        doctors = new ArrayList<Doctor>();
        consultations = new ArrayList<Consultation>();

    }

    /**
     * @param firstName            doctor's first name
     * @param lastName             doctor's last name
     * @param age                  doctor's age
     * @param identificationNumber identificationNumber doctor's identification number, is unique
     * @return true if doctor was added successfully, false otherwise (to be used in Tests)
     */
    public Boolean addDoctor(String firstName, String lastName, int age, Long identificationNumber) {

        doctors.add(new Doctor(firstName, lastName, age, identificationNumber));
        return true;
    }

    /**
     * each doctor is identified by their identificationNumber which is unique
     *
     * @param identificationNumber doctor's identification number, is unique
     * @return true if doctor was deleted successfully, false otherwise (to be used in Tests)
     */
    public Doctor deleteDoctor(Long identificationNumber) {

        for (Doctor doctor : doctors
        ) {
            if (doctor.equals(identificationNumber)) {
                doctors.remove(doctor);
                return doctor;
            }
        }

        return null;
    }

    /**
     * @param identificationNumber doctor's identification number, is unique
     * @return true if the doctor was already added, false otherwise
     */
    public Boolean findDoctor(Long identificationNumber) {

        for (Doctor doctor : doctors
        ) {
            if (doctor.equals(identificationNumber))
                return true;
        }

        return false;
    }

    /**
     * @return a list containing all doctors
     */
    public List<Doctor> findAll() {
        return this.doctors;
    }

    /**
     * @return a list containing all doctors and their data (number of patients, shift time, money billed)
     */
    public List<String> doctorsSummary() {
        List<String> doctorsList = new ArrayList<String>();
        for (Doctor doctor : this.findAll()
        ) {
            doctorsList.add(doctor.toString());
        }
        return doctorsList;
    }

    /**
     *
     * @throws FileNotFoundException
     */
    public void saveToFile() throws FileNotFoundException {
        PrintWriter output = new PrintWriter(file);
        for (Doctor doctor : this.findAll()
        ) {
            output.write(doctor.toString() + "\n");
        }
        output.close();
    }
}
