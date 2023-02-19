package com.peicheng.student;

import com.peicheng.Main;
import com.peicheng.exception.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Service
public class StudentService {

    private final static Logger LOGGER = LoggerFactory.getLogger(StudentService.class);

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudents(){
        LOGGER.info("getStudents was called");
        return studentRepository.findAll();
    }

    public Student getStudent(Integer id){
        LOGGER.info("getStudent was called with arg {}", id);
        return studentRepository.findById(id)
                .orElseThrow(() -> {
                    NotFoundException notFoundException = new NotFoundException("student with id " + id + " not found");
                    LOGGER.error("error in getting student {}", id, notFoundException);
                    return notFoundException;
                });
    }

    public void addStudent(Student newStudentRequest){
        LOGGER.info("addStudent was called with arg {}", newStudentRequest.toString());
        Student student = new Student();
        student.setName(newStudentRequest.getName());
        student.setEmail(newStudentRequest.getEmail());
        student.setAge(newStudentRequest.getAge());
        studentRepository.save(student);
    }

    public void deleteStudent(Integer id){
        LOGGER.info("deleteStudent was called with arg {}", id);
        studentRepository.deleteById(id);
    }

    public void updateStudent(Student newStudentRequest, Integer id){
        LOGGER.info("updateStudent was called with arg {}, {}", newStudentRequest.toString(), id);
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> {
                    NotFoundException notFoundException = new NotFoundException("student with id " + id + " not found");
                    LOGGER.error("error in updating student {}", id, notFoundException);
                    return notFoundException;
                });
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
