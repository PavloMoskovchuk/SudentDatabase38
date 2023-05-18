package org.example;

import org.example.domain.Student;
import org.example.repository.StudentLocalRepository;
import org.example.repository.StudentRepository;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        StudentRepository studentRepository = new StudentLocalRepository();
        Student student2 = Student.builder()
                .firstName("Boris")
                .lastName("Jonson")
                .age(45)
                .groupNumber("PAR")
                .build();
        studentRepository.save(student2);

        List<Student> students = studentRepository.findAll();
        System.out.println(students);


    }
}
