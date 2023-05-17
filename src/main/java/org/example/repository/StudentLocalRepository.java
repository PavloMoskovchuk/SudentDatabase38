package org.example.repository;

import org.example.domain.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentLocalRepository implements StudentRepository {

    private static final String DB_URL = "jdbc:mysql://127.0.0.1:3306/student";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "Palbor1948!$";


    private static final String SHOW_STUDENTS = "SELECT * FROM student";
//    private static final String SHOW_STUDENT_BY_ID = "SELECT * FROM student where id = ?";
    private static final String ADD_STUDENT = "INSERT INTO student (firstName, lastName, age, groupNumber) VALUES (?, ?, ?, ?)";

    @Override
    public void save(Student student) {
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            PreparedStatement ps = conn.prepareStatement(ADD_STUDENT);
            ps.setString(1, student.getFirstName());
            ps.setString(2, student.getLastName());
            ps.setInt(3, student.getAge());
            ps.setString(4, student.getGroupNumber());
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Student> findAll() {
        List<Student> result = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(SHOW_STUDENTS);) {
            while (rs.next()) {
                Student student = Student.builder()
                        .id(rs.getInt("id"))
                        .firstName(rs.getString("firstName"))
                        .lastName(rs.getString("lastName"))
                        .age(rs.getInt("age"))
                        .groupNumber(rs.getString("groupNumber"))
                        .build();

                result.add(student);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
}