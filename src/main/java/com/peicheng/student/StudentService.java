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

    public Student getStudent(Integer id){
        return studentRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("student with id " + id + " not found"));
    }

    public void addStudent(Student newStudentRequest){
        Student student = new Student();
        student.setName(newStudentRequest.getName());
        student.setEmail(newStudentRequest.getEmail());
        student.setAge(newStudentRequest.getAge());
        studentRepository.save(student);
    }

    public void deleteStudent(Integer id){
        studentRepository.deleteById(id);
    }

    public void updateStudent(Student newStudentRequest, Integer id){
        Student student = studentRepository.findById(id).get();
        if(newStudentRequest.getName() != null){
            student.setName(newStudentRequest.getName());
        }
        if(newStudentRequest.getAge() != null){
            student.setAge(newStudentRequest.getAge());
        }
        if(newStudentRequest.getEmail() != null){
            student.setEmail(newStudentRequest.getEmail());
        }
        studentRepository.save(student);
    }
}
