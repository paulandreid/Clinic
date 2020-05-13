# Clinic
A Clinic with 4 rooms, multiple doctors and patients - implementation in Java (JDK 8).

The application starts by running the Main Class which then will use the UI Class to let the user select from the console the options desired to be tested.

Firstly, the lists of doctors and patients must be generated. This is implemented in ViewGenerating CLass, simulating user input of doctors and patients. 
These lists are not linked with one another, so patients don't have a doctor assigned yet and the rooms are empty. Also doctors do not have shift times assigned to them. For this to happen,
the option "f1. Print a summary of the doctors" must be selected. This will assign doctors their shift time (of maximum 7 hours), in one room at a time (a doctor can have a shift time of 1 hour
in room "1" then 2 hours in room "2") and patients will also be assigned to their doctors.



Program Structure:

The program consists of 13 Classes:

	1.Main: the main class which must be run
	2.UI: lets user choose options with keyboard input
	3.ViewGenerating: generates user input for doctor/patient list
	4.Controller: validates data to be sent to repositorie, raises exceptions if necessary 
	5.Repositories: gestionates and stores data in form of List<>, saves data to files
		5a.DoctorRepository: handles data for model class Doctor
		5b.PatientRepository: handles data for model class Patient
		5c.RoomRepository: handles data for model class Room
	6.Model Classes:
		6a.Doctor
		6b.Patient
		6c.Room
	7.Consultation: enum representing the reasons a patient came to the clinic
	8.Exceptions:
		8a.DataAlreadyExists: thrown if the data was already inserted
		8b.InvalidDataException: thrown if the data input is invalid (eg. negative age)



