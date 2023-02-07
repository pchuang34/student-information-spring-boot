package com.peicheng;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@SpringBootApplication
@RestController
@RequestMapping("api/v1/students")
public class Main {

    private final StudentRepository studentRepository;

    public Main(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @GetMapping
    public List<Student> getStudents(){
        return studentRepository.findAll();
    }

    record NewStudentRequest(
            String name,
            String email,
            Integer age
    ) {}

    @PostMapping
    public void addStudent(@RequestBody NewStudentRequest newStudentRequest){
        Student student = new Student();
        student.setName(newStudentRequest.name);
        student.setEmail(newStudentRequest.email);
        student.setAge(newStudentRequest.age);
        studentRepository.save(student);
    }

    @DeleteMapping("{studentId}")
    public void deleteStudent(@PathVariable("studentId") Integer id){
        studentRepository.deleteById(id);
    }

    @PutMapping("{studentId}")
    public void updateStudent(@RequestBody NewStudentRequest newStudentRequest,
                              @PathVariable("studentId") Integer id){
        Student student = studentRepository.findById(id).get();
        if(newStudentRequest.name != null){
            student.setName(newStudentRequest.name);
        }
        if(newStudentRequest.age != null){
            student.setAge(newStudentRequest.age);
        }
        if(newStudentRequest.email != null){
            student.setEmail(newStudentRequest.email);
        }
        studentRepository.save(student);
    }
}
