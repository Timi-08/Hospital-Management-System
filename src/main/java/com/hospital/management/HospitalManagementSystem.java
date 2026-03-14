package com.hospital.management;

import com.hospital.management.DAO.*;
import com.hospital.management.model.*;
import com.hospital.management.reports.PatientDoctorReport;

import javax.print.Doc;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;


public class HospitalManagementSystem {

    static Scanner scanner = new Scanner(System.in);

    static PatientDAO patientDAO = new PatientDAO();
    static DoctorDAO doctorDAO = new DoctorDAO();
    static AppointmentDAO appointmentDAO = new AppointmentDAO();

    public static void main(String[] args) {

        while (true) {

            System.out.println("\n===== HOSPITAL MANAGEMENT SYSTEM =====");
            System.out.println("1. Patient");
            System.out.println("2. Doctor");
            System.out.println("3. Appointments");
            System.out.println("4. Assigned Patients");
            System.out.println("5. Exit");

            System.out.print("Choose option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {

                case 1:
                    patientMenu();
                    break;

                case 2:
                    doctorMenu();
                    break;

                case 3:
                    appointmentMenu();
                    break;

                case 4:
                    assignmentMenu();
                    break;

                case 5:
                    System.out.println("Exiting System...");
                    return;

                default:
                    System.out.println("Invalid Option.");
            }
        }
    }

    // ================= PATIENT MENU =================

    public static void patientMenu() {

        while (true) {

            System.out.println("\n---- PATIENT MENU ----");
            System.out.println("1. Add Patient");
            System.out.println("2. Update Patient");
            System.out.println("3. Delete Patient");
            System.out.println("4. View All Patients");
            System.out.println("5. Find Patient by Name");
            System.out.println("6. Exit");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {

                case 1:

                    System.out.print("Name: ");
                    String name = scanner.nextLine();

                    System.out.print("Age: ");
                    int age = scanner.nextInt();
                    scanner.nextLine();

                    System.out.print("Gender: ");
                    String gender = scanner.nextLine();

                    System.out.print("Phone Number: ");
                    String phoneNumber = scanner.nextLine();

                    System.out.print("Illness: ");
                    String illnessDescription = scanner.nextLine();

                    Patient patient = new Patient(name, age, gender, phoneNumber, illnessDescription);
                    patientDAO.addPatient(patient);
                    break;

                case 2:
                    System.out.print("Enter Patient ID: ");
                    int updateId = scanner.nextInt();
                    scanner.nextLine();

                    System.out.print("New Name: ");
                    String newName = scanner.nextLine();

                    System.out.print("New Age: ");
                    int newAge = scanner.nextInt();
                    scanner.nextLine();

                    System.out.print("New Gender: ");
                    String newGender = scanner.nextLine();

                    System.out.print("New Phone: ");
                    String newPhone = scanner.nextLine();

                    System.out.print("New Illness: ");
                    String newIllness = scanner.nextLine();

                    Patient updatedPatient = new Patient(newName, newAge, newGender, newPhone, newIllness);
                    updatedPatient.setPatientId(updateId);

                    patientDAO.updatePatient(updatedPatient);
                    break;

                case 3:
                    System.out.print("Enter Patient ID to delete: ");
                    int deleteId = scanner.nextInt();
                    patientDAO.deletePatient(deleteId);
                    break;

                case 4:
                    List<Patient> patients = patientDAO.getAllPatients();
                    patients.forEach(System.out::println);
                    break;

                case 5:
                    System.out.print("Enter name: ");
                    String searchName = scanner.nextLine();

                    List<Patient> results = patientDAO.findPatientsByName(searchName);
                    results.forEach(System.out::println);
                    break;

                case 6:
                    return;

                default:
                    System.out.println("Invalid option.");
            }
        }
    }

    // ================= DOCTOR MENU =================

    public static void doctorMenu() {

        while (true) {

            System.out.println("\n---- DOCTOR MENU ----");
            System.out.println("1. Add Doctor");
            System.out.println("2. Update Doctor");
            System.out.println("3. Delete Doctor");
            System.out.println("4. View All Doctors");
            System.out.println("5. Find Doctor by Name");
            System.out.println("6. Exit");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {

                case 1:

                    System.out.print("Doctor Name: ");
                    String name = scanner.nextLine();

                    System.out.print("Specialization: ");
                    String specialization = scanner.nextLine();

                    Doctor doctor = new Doctor(name, specialization);
                    doctorDAO.addDoctor(doctor);
                    break;

                case 2:
                    System.out.print("Enter Doctor ID: ");
                    int updateId = scanner.nextInt();
                    scanner.nextLine();

                    System.out.print("Updated Name: ");
                    String newName = scanner.nextLine();

                    System.out.print("Updated Specialization: ");
                    String newSpecialization = scanner.nextLine();

                    Doctor updatedDoctor = new Doctor(newName, newSpecialization);
                    updatedDoctor.setDoctorId(updateId);

                    doctorDAO.updateDoctor(updatedDoctor);

                    break;

                case 3:
                    System.out.print("Enter Doctor ID to delete: ");
                    int deleteId = scanner.nextInt();
                    doctorDAO.deleteDoctor(deleteId);
                    break;

                case 4:
                    List<Doctor> doctors = doctorDAO.getAllDoctors();
                    doctors.forEach(System.out::println);
                    break;

                case 5:

                    System.out.print("Enter doctor name: ");
                    String search = scanner.nextLine();

                    List<Doctor> result = doctorDAO.findDoctorsByName(search);
                    result.forEach(System.out::println);
                    break;

                case 6:
                    return;

                default:
                    System.out.println("Invalid option.");
            }
        }
    }

    // ================= APPOINTMENT MENU =================

    public static void appointmentMenu() {

        while (true) {

            System.out.println("\n---- APPOINTMENT MENU ----");
            System.out.println("1. Create Appointment");
            System.out.println("2. View All Appointments");
            System.out.println("3. Exit");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {

                case 1:

                    System.out.print("Patient ID: ");
                    int patientId = scanner.nextInt();

                    System.out.print("Doctor ID: ");
                    int doctorId = scanner.nextInt();
                    scanner.nextLine();

                    System.out.print("Date (DD/MM/YYYY): ");
                    String dateInput = scanner.nextLine();

                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                    LocalDate date = LocalDate.parse(dateInput, formatter);

                    Appointment appointment = new Appointment(patientId, doctorId, date);

                    appointmentDAO.addAppointment(appointment);
                    break;

                case 2:

                    appointmentDAO.getAllAppointments();
                    break;

                case 3:
                    return;

                default:
                    System.out.println("Invalid option.");
            }
        }
    }

    // ================= ASSIGNMENT MENU =================

    public static void assignmentMenu() {

        while (true) {

            System.out.println("\n---- ASSIGN PATIENTS ----");
            System.out.println("1. Assign Doctor To Patient");
            System.out.println("2. View Assigned Patients");
            System.out.println("3. Exit");

            int choice = scanner.nextInt();

            switch (choice) {

                case 1:

                    System.out.print("Patient ID: ");
                    int patientId = scanner.nextInt();

                    System.out.print("Doctor ID: ");
                    int doctorId = scanner.nextInt();

                    PatientDoctorReport.assignDoctor(patientId, doctorId);
                    break;

                case 2:

                    PatientDoctorReport.viewAssignedPatients();
                    break;

                case 3:
                    return;

                default:
                    System.out.println("Invalid option.");
            }
        }
    }
}