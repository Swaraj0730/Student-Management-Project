package com.StudentManagement.mapper;

import com.StudentManagement.Entity.Student;
import com.StudentManagement.dto.StudentDto;

public class StudentMapper {

    public static StudentDto maptoStudentDto(Student student){
        StudentDto studentDto = new StudentDto(
                student.getId(),
                student.getFirstName(),
                student.getLastName(),
                student.getEmail()
        );
        return studentDto;
    }

    public static Student maptoStudent(StudentDto studentDto){
        Student student = new Student(
                studentDto.getId(),
                studentDto.getFirstName(),
                studentDto.getLastName(),
                studentDto.getEmail()
        );
        return student;
    }
}
