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
    public Student getStudent(@PathVariable("studentId") Integer id){
        return studentService.getStudent(id);
    }

    //exception testing
    @GetMapping(path = "{studentId}/exception")
    Student getCustomerException(@PathVariable("studentId") Integer id) {
        throw new ApiRequestException(
                "ApiRequestException for customer " + id
        );
    }

    @PostMapping
    public void addStudent(@Valid @RequestBody Student newStudentRequest){
//        Student student = new Student();
//        student.setName(newStudentRequest.name);
//        student.setEmail(newStudentRequest.email);
//        student.setAge(newStudentRequest.age);
        studentService.addStudent(newStudentRequest);
    }

    @DeleteMapping("{studentId}")
    public void deleteStudent(@PathVariable("studentId") Integer id){
        studentService.deleteStudent(id);
    }

    @PutMapping("{studentId}")
    public void updateStudent(@RequestBody Student newStudentRequest,
                              @PathVariable("studentId") Integer id){
        studentService.updateStudent(newStudentRequest, id);
    }

}
