package com.StudentManagement.service;

import com.StudentManagement.Entity.Student;
import com.StudentManagement.dto.StudentDto;
import jakarta.validation.Valid;

import java.util.List;

public interface StudentService {

    List<StudentDto> getAllStudent();

    void createStudent(StudentDto student);

    StudentDto getStudentById(long studentId);

    void updateStudent(StudentDto studentDto);

    void deleteStudent(long studentId);
}
