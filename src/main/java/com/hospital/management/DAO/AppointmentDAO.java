package com.hospital.management.DAO;

import com.hospital.management.config.DBConnection;
import com.hospital.management.model.Appointment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AppointmentDAO {

    public void addAppointment(Appointment appointment) {

        String sql = "INSERT INTO appointments (patient_id, doctor_id, appointment_date) VALUES (?, ?, ?)";

        try {

            Connection conn = DBConnection.getConnection();

            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setInt(1, appointment.getPatientId());
            stmt.setInt(2, appointment.getDoctorId());
            stmt.setDate(3, java.sql.Date.valueOf(appointment.getAppointmentDate()));

            stmt.executeUpdate();

            System.out.println("Appointment created successfully.");

        } catch (SQLException e) {
            System.out.println("Error creating appointment.");
            e.printStackTrace();
        }
    }
    public void getAllAppointments() {

        String sql = "SELECT a.appointment_id, p.name AS patient_name, d.name AS doctor_name, a.appointment_date " +
                "FROM appointments a " +
                "JOIN patients p ON a.patient_id = p.patient_id " +
                "JOIN doctors d ON a.doctor_id = d.doctor_id";

        try {

            Connection conn = DBConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            System.out.println("\nALL APPOINTMENTS");
            System.out.println("------------------------------------");

            while (rs.next()) {

                int id = rs.getInt("appointment_id");
                String patientName = rs.getString("patient_name");
                String doctorName = rs.getString("doctor_name");
                java.sql.Date date = rs.getDate("appointment_date");

                System.out.println("Appointment ID: " + id);
                System.out.println("Patient Name: " + patientName);
                System.out.println("Doctor Name: " + doctorName);
                System.out.println("Date: " + date);
                System.out.println("------------------------------------");
            }

        } catch (SQLException e) {
            System.out.println("Error retrieving appointments.");
            e.printStackTrace();
        }
    }
}