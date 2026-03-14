package com.hospital.management.model;

public class Patient {

    private int patientId;
    private String name;
    private int age;
    private String gender;
    private String phoneNumber;
    private String illnessDescription;

    public Patient() {}

    public Patient(String name, int age, String gender, String phoneNumber, String illnessDescription) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        this.illnessDescription = illnessDescription;
    }

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getIllnessDescription() {
        return illnessDescription;
    }

    public void setIllnessDescription(String illnessDescription) {
        this.illnessDescription = illnessDescription;
    }

    @Override
    public String toString() {
        return "PatientId: " + patientId +
                ", Name: " + name +
                ", Age: " + age +
                ", Gender: " + gender  +
                ", Phone Number: " + phoneNumber +
                ", Illness Description: " + illnessDescription;
    }
}