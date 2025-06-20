package com.StudentManagement.controller;

import com.StudentManagement.Entity.Student;
import com.StudentManagement.dto.StudentDto;
import com.StudentManagement.mapper.StudentMapper;
import com.StudentManagement.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class StudentController {

    private StudentService studentService ;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    //handler method to handle student list
    @GetMapping("/students")
    public String listStudents(Model model){
        List<StudentDto> students = studentService.getAllStudent();
        model.addAttribute("students",students) ;
        return "students";
    }

    //handler method to handle a new student request
    @GetMapping("/students/new")
    public String newStudent(Model model){
        StudentDto studentDto = new StudentDto();
        model.addAttribute("student",studentDto);
        return "create-student" ;
    }

    //handler method to save a new student
    @PostMapping("/students")
    public String saveStudent(@Valid @ModelAttribute("student") StudentDto student,
                                BindingResult result,
                              Model model){
        if(result.hasErrors()){
            model.addAttribute("student",student);
            return "create-student" ;
        }
        studentService.createStudent(student);
        return "redirect:/students";
    }

    //handler method to update the existing students
    @GetMapping("students/{studentId}/edit")
    public String editStudent(@PathVariable("studentId") long studentId,
                              Model model){
        StudentDto student = studentService.getStudentById(studentId);
        model.addAttribute("student",student);
        return"edit-student";
    }

    //handler method to handle edit student form submit request
    @PostMapping("/students/{studentId}")
    public String updateStudent(@PathVariable("studentId")long studentId,
                                @Valid @ModelAttribute("student") StudentDto studentDto,
                                BindingResult result,
                                Model model){
        if(result.hasErrors()){
            model.addAttribute("student",studentDto);
            return "edit-student";
        }
        studentDto.setId(studentId);
        studentService.updateStudent(studentDto);
        return "redirect:/students" ;
    }

    //handler method for deleting a student
    @GetMapping("/students/{studentId}/delete")
    public String DeleteStudent(@PathVariable("studentId") long studentId){
        studentService.deleteStudent(studentId);
        return "redirect:/students";
    }

    //handler method to handle view student request
    @GetMapping("/students/{studentId}/view")
    public String viewStudent(@PathVariable("studentId") long studentId,
                              Model model){
        StudentDto studentDto = studentService.getStudentById(studentId);
        model.addAttribute("student",studentDto);
        return "view-student" ;
    }
}
