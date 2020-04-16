package com.Controller;

import com.Model.Consultation;
import com.Model.Patient;
import com.Model.Doctor;
import com.Model.Room;
import com.Repository.*;
import com.exceptions.DataAlreadyExists;
import com.exceptions.InvalidDataException;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Controller {

    private DoctorRepository doctorRepository;
    private PatientRepository patientRepository;
    private RoomRepository roomRepository;

    public Controller() {
        roomRepository = new RoomRepository();
        doctorRepository = new DoctorRepository();
        patientRepository = new PatientRepository();
    }

    /**
     * @param firstName            first name of doctor
     * @param lastName             last name of doctor
     * @param age                  age of doctor
     * @param identificationNumber doctor's identification number, is unique
     * @return true if Doctor was added successfully, false otherwise (to be used in Tests)
     * @throws DataAlreadyExists    in case of adding a Doctor that already was inserted
     * @throws InvalidDataException in case of negative age or Identification Number
     */
    public Boolean addDoctor(String firstName, String lastName, int age, Long identificationNumber) throws DataAlreadyExists, InvalidDataException {
        if (age >= 30 && age <= 65 && Float.valueOf(identificationNumber) >= 999) {
            if (!doctorRepository.findDoctor(identificationNumber)) {
                doctorRepository.addDoctor(firstName, lastName, age, identificationNumber);
                return true;
            } else
                throw new DataAlreadyExists("Doctor already exists.");
        } else
            throw new InvalidDataException("Invalid data when adding a Doctor.");
    }

    /**
     * @param identificationNumber doctor's identification number, is unique
     * @return true if Doctor was deleted successfully, false otherwise (to be used in Tests)
     */
    public Boolean deleteDoctor(Long identificationNumber) {
        return doctorRepository.deleteDoctor(identificationNumber) != null;
    }

    /**
     * @param firstName    first name of patient
     * @param lastName     last name of patient
     * @param age          age of patient
     * @param consultation reason for consultation
     * @return true if patient was added successfully, false otherwise (to be used in Tests)
     * @throws InvalidDataException in case of negative age or age>85 or if consultation was inserted wrong
     * @throws DataAlreadyExists    in case of adding a Patient that already was inserted with the same consultation
     */
    public Boolean addPatient(String firstName, String lastName, int age, String consultation) throws InvalidDataException, DataAlreadyExists {
        if (age < 0 || age > 85)
            throw new InvalidDataException("Invalid Data when adding a Patient. Incorrect age.");
        else {
            Consultation stringToConsultation;
            switch (consultation) {
                case "consultation":
                    stringToConsultation = Consultation.consultation;
                    break;
                case "prescription":
                    stringToConsultation = Consultation.prescription;
                    break;
                case "treatment":
                    stringToConsultation = Consultation.treatment;
                    break;
                default:
                    throw new InvalidDataException("Invalid Data when adding a Patient. Incorrect consultation");
            }
            if (!patientRepository.findPerson(firstName, lastName, age, stringToConsultation)) {
                patientRepository.addPatient(firstName, lastName, age, stringToConsultation);
                return true;
            } else
                throw new DataAlreadyExists("Patient with same consultation already exists.");
        }

    }

    /**
     * @param firstName
     * @param lastName
     * @param age
     * @param consultation
     * @return
     */
    public Boolean deletePatient(String firstName, String lastName, int age, Consultation consultation) {
        return patientRepository.deletePatient(firstName, lastName, age, consultation) != null;
    }

    /**
     * @return a list of strings containing the data of all patients
     */
    public List<String> printAllPatients() {
        List<String> patients = new ArrayList<String>();
        for (Patient patient : this.patientRepository.findAll()
        ) {
            patients.add(patient.toString());
        }
        return patients;
    }

    /**
     * if called before doctorsSummary() the doctors will not be assigned to rooms
     * and no patients will be consulted
     *
     * @return a list of strings containing the data of all patients
     */
    public List<String> printAllDoctors() {
        List<String> doctors = new ArrayList<String>();
        for (Doctor doctor : this.doctorRepository.findAll()
        ) {
            doctors.add(doctor.toString());
        }
        return doctors;
    }

    /**
     * @return list of all patients in their age group
     */
    public List<Long> patientsSummary() {
        return this.patientRepository.patientsSummary();
    }

    /**
     * first doctors are assigned to their rooms
     * then patients are assigned to doctors
     *
     * @return a list of doctors containing all thei data, including worked time and the number of patients consulted
     */
    public List<String> doctorsSummary() {
        this.assignDoctorsToRoom();
        this.assignPatientToDoctor();
        return this.doctorRepository.doctorsSummary();
    }

    /**
     * generates a random shift time in 30 minutes increments that is smaller than 7 hours
     * then assigns a doctor to a room where the doctor's shift time doesn't exceed the 7AM-19PM program
     * doctors can have multiple shifts and in different rooms
     */
    private void assignDoctorsToRoom() {
        for (Doctor doctor : this.doctorRepository.findAll()
        ) {

            while (true) {
                int shiftTime = new Random().nextInt(14) * 30;
                if (doctor.getShiftTime() + shiftTime <= 420)
                    if (!this.roomRepository.assignDoctor(doctor, shiftTime))
                        break;
                    else
                        doctor.setShiftTime(doctor.getShiftTime() + shiftTime);
                if (doctor.getShiftTime() + shiftTime > 420)
                    break;
            }
        }
    }

    /**
     * each doctor can consult multiple patients
     * as long as the shift time is not exceed
     */
    private void assignPatientToDoctor() {
        for (Patient patient : this.patientRepository.findAll()
        ) {
            for (Doctor doctor : this.doctorRepository.findAll()
            ) {
                if (patient.getAppointment().getTime() + doctor.getTimeSpentConsulting() <= doctor.getShiftTime() && !patient.getConsulted()) {
                    doctor.addPatient(patient);
                    doctor.setTimeSpentConsulting(doctor.getTimeSpentConsulting() + patient.getAppointment().getTime());
                    patient.setConsulted(true);
                }
            }
        }
    }

    /**
     * @return the remainig patients which were not consulted
     */
    public List<String> printPatientsNotConsulted() {
        return this.patientRepository.patientsNotConsulted();
    }

    public void saveDoctorsToFile() {
        try {
            this.doctorRepository.saveToFile();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

    }

    public void savePateintsToFile() {
        try {
            this.patientRepository.saveToFile();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}
