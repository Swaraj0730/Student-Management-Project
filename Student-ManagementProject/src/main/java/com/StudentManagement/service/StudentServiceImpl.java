package com.StudentManagement.service;

import com.StudentManagement.Entity.Student;
import com.StudentManagement.dto.StudentDto;
import com.StudentManagement.mapper.StudentMapper;
import com.StudentManagement.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService{

    private StudentRepository studentRepository ;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public List<StudentDto> getAllStudent() {
        List<Student> student = studentRepository.findAll();
        List<StudentDto> studentDtos = student.stream()
                .map((students)->
                                StudentMapper.maptoStudentDto(students)
                        ).collect(Collectors.toList());
        return studentDtos;
    }

    @Override
    public void createStudent(StudentDto studentDto) {
        Student student = StudentMapper.maptoStudent(studentDto);
        studentRepository.save(student);
    }

    @Override
    public StudentDto getStudentById(long studentId) {
        Student student = studentRepository.findById(studentId).get();
        StudentDto studentDto =  StudentMapper.maptoStudentDto(student);
        return studentDto;
    }

    @Override
    public void updateStudent(StudentDto studentDto) {
        studentRepository.save(StudentMapper.maptoStudent(studentDto));

    }

    @Override
    public void deleteStudent(long studentId) {
        studentRepository.deleteById(studentId);
    }

}
