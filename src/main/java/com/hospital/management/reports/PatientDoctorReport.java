package com.hospital.management.reports;

import com.hospital.management.config.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PatientDoctorReport {

    public static void assignDoctor(int patientId, int doctorId) {

        String sql = "UPDATE patients SET doctor_id = ? WHERE patient_id = ?";

        try {
            Connection conn = DBConnection.getConnection();

            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setInt(1, doctorId);
            stmt.setInt(2, patientId);

            int rowsUpdated = stmt.executeUpdate();

            if (rowsUpdated > 0) {
                System.out.println("Doctor successfully assigned to patient.");
            } else {
                System.out.println("Patient not found.");
            }

        } catch (SQLException e) {
            System.out.println("Error assigning Doctor");
        }
    }

    public static void viewAssignedPatients() {

        String sql = "SELECT p.patient_id, p.name AS patient_name, d.name AS doctor_name " +
                "FROM patients p " +
                "JOIN doctors d ON p.doctor_id = d.doctor_id";

        try {
            Connection conn = DBConnection.getConnection();

            PreparedStatement stmt = conn.prepareStatement(sql);

            ResultSet rs = stmt.executeQuery();

            System.out.println("\nASSIGNED PATIENTS");
            System.out.println("-----------------------------");

            while (rs.next()) {

                int patientId = rs.getInt("patient_id");
                String patientName = rs.getString("patient_name");
                String doctorName = rs.getString("doctor_name");

                if (doctorName == null) {
                    doctorName = "No Doctor Assigned";
                }

                System.out.println("Patient ID: " + patientId);
                System.out.println("Patient Name: " + patientName);
                System.out.println("Doctor: " + doctorName);
                System.out.println("-----------------------------");
            }

        } catch (SQLException e) {
            System.out.println("Error retrieving assigned patients.");
        }
    }
}