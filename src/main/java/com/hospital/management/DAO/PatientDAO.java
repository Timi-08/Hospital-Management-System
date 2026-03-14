package com.hospital.management.DAO;

import com.hospital.management.config.DBConnection;
import com.hospital.management.model.Patient;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PatientDAO {

    public void addPatient(Patient patient) {
        String sql = "INSERT INTO patients (name, age, gender, phone_number, illness_description) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, patient.getName());
            stmt.setInt(2, patient.getAge());
            stmt.setString(3, patient.getGender());
            stmt.setString(4, patient.getPhoneNumber());
            stmt.setString(5, patient.getIllnessDescription());
            stmt.executeUpdate();
            System.out.println("Patient added successfully.");
        } catch (SQLException e) {
            System.out.println("Error adding patient: " + e.getMessage());
        }
    }

    public List<Patient> getAllPatients() {
        List<Patient> patients = new ArrayList<>();
        String sql = "SELECT * FROM patients";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Patient patient = new Patient();
                patient.setPatientId(rs.getInt("patient_id"));
                patient.setName(rs.getString("name"));
                patient.setAge(rs.getInt("age"));
                patient.setGender(rs.getString("gender"));
                patient.setPhoneNumber(rs.getString("phone_number"));
                patient.setIllnessDescription(rs.getString("illness_description"));
                patients.add(patient);
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving patients: " + e.getMessage());
        }
        return patients;
    }

    public void updatePatient(Patient patient) {
        String sql = "UPDATE patients SET name=?, age=?, gender=?, phone_number=?, illness_description=? WHERE patient_id=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, patient.getName());
            stmt.setInt(2, patient.getAge());
            stmt.setString(3, patient.getGender());
            stmt.setString(4, patient.getPhoneNumber());
            stmt.setString(5, patient.getIllnessDescription());
            stmt.setInt(6, patient.getPatientId());
            stmt.executeUpdate();
            System.out.println("Patient updated successfully.");
        } catch (SQLException e) {
            System.out.println("Error updating patient: " + e.getMessage());
        }
    }

    public void deletePatient(int patientId) {
        String sql = "DELETE FROM patients WHERE patient_id=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, patientId);
            stmt.executeUpdate();
            System.out.println("Patient deleted successfully.");
        } catch (SQLException e) {
            System.out.println("Error deleting patient: " + e.getMessage());
        }
    }
    public List<Patient> findPatientsByName(String name) {

        List<Patient> patients = new ArrayList<>();

        String sql = "SELECT * FROM patients WHERE name LIKE ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, "%" + name + "%");

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                Patient patient = new Patient();

                patient.setPatientId(rs.getInt("patient_id"));
                patient.setName(rs.getString("name"));
                patient.setAge(rs.getInt("age"));
                patient.setGender(rs.getString("gender"));
                patient.setPhoneNumber(rs.getString("phone_number"));
                patient.setIllnessDescription(rs.getString("illness_description"));

                patients.add(patient);
            }

        } catch (Exception e) {
            System.out.println("Error finding patient");
        }

        return patients;
    }
}