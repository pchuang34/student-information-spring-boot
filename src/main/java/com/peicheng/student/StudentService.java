package com.peicheng.student;

import com.peicheng.Main;
import com.peicheng.exception.NotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    record NewStudentRequest(
            String name,
            String email,
            Integer age
    ) {}

    public List<Student> getStudents(){
        return studentRepository.findAll();
    }

    public Student getStudent(Long id){
        return studentRepository.findAll()
                .stream()
                .filter(student -> student.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new NotFoundException("student with id " + id + " not found"));
    }

//    @PostMapping
//    public void addStudent(@RequestBody NewStudentRequest newStudentRequest){
//        Student student = new Student();
//        student.setName(newStudentRequest.name);
//        student.setEmail(newStudentRequest.email);
//        student.setAge(newStudentRequest.age);
//        studentRepository.save(student);
//    }

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
