package com.peicheng.student;

import com.peicheng.exception.ApiRequestException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/students")
public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public List<Student> getStudents() {
        return studentService.getStudents();
    }

    @GetMapping(path = "{studentId}")
    public Student getStudent(@PathVariable("studentId") Long id){
        return studentService.getStudent(id);
    }

    @GetMapping(path = "{studentId}/exception")
    Student getCustomerException(@PathVariable("studentId") Long id) {
        throw new ApiRequestException(
                "ApiRequestException for customer " + id
        );
    }

    @PostMapping
    public void addStudent(@RequestBody @Valid Student newStudentRequest){
//        Student student = new Student();
//        student.setName(newStudentRequest.name);
//        student.setEmail(newStudentRequest.email);
//        student.setAge(newStudentRequest.age);
//        studentRepository.save(student);
    }

}
