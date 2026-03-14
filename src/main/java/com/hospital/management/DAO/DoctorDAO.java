package com.hospital.management.DAO;

import com.hospital.management.config.DBConnection;
import com.hospital.management.model.Doctor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DoctorDAO {

    public void addDoctor(Doctor doctor) {

        String sql = "INSERT INTO doctors (name, specialization) VALUES (?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, doctor.getName());
            stmt.setString(2, doctor.getSpecialization());

            int rows = stmt.executeUpdate();

            if (rows > 0) {
                System.out.println("Doctor added successfully.");
            }

        } catch (SQLException e) {
            System.out.println("Error adding doctor: " + e.getMessage());
        }
    }
    public void updateDoctor(Doctor doctor){
        String sql= "UPDATE doctors SET name=?, specialization=? WHERE doctor_id=?";
        try(Connection conn = DBConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setString(1,doctor.getName());
            stmt.setString(2, doctor.getSpecialization());
            stmt.setInt(3, doctor.getDoctorId());
            stmt.executeUpdate();
            System.out.println("Doctor updated successfully.");
        } catch (SQLException e) {
            System.out.println("Error updating doctor: "+ e.getMessage());
        }
    }

    public void deleteDoctor(int doctorId){
        String sql = "DELETE from patients WHERE doctor_id=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, doctorId);
            stmt.executeUpdate();
            System.out.println("Doctor deleted successfully.");
        } catch (SQLException e) {
            System.out.println("Error deleting patient: " + e.getMessage());
        }
    }

    public List<Doctor> findDoctorsByName(String name) {

        List<Doctor> doctors = new ArrayList<>();

        String sql = "SELECT * FROM doctors WHERE name LIKE ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, "%" + name + "%");

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                Doctor doctor = new Doctor();

                doctor.setDoctorId(rs.getInt("doctor_id"));
                doctor.setName(rs.getString("name"));
                doctor.setSpecialization(rs.getString("specialization"));

                doctors.add(doctor);
            }

        } catch (Exception e) {
            System.out.println("Error finding doctor "+ e.getMessage());
        }

        return doctors;
    }
    public List<Doctor> getAllDoctors() {

        List<Doctor> doctors = new ArrayList<>();

        String sql = "SELECT * FROM doctors";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {

                Doctor doctor = new Doctor();

                doctor.setDoctorId(rs.getInt("doctor_id"));
                doctor.setName(rs.getString("name"));
                doctor.setSpecialization(rs.getString("specialization"));

                doctors.add(doctor);
            }

        } catch (SQLException e) {
            System.out.println("Error retrieving doctors: " + e.getMessage());
        }

        return doctors;
    }


}