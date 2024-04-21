package com.example.demo.student;

import jakarta.persistence.Id;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }


    public List<Student> getStudents(){
        return studentRepository.findAll();
    }

    // POST
    public void addStudent(Student student) {
        Optional<Student> studentByEmail = studentRepository.findStudentByEMail(student.geteMail());
        if (studentByEmail.isPresent()){
            throw new IllegalStateException("email taken");
        }
        studentRepository.save(student);
    }

    // DELETE
    public void deleteStudent(Long id) {
        boolean exists = studentRepository.existsById(id);
        if(!exists){
            throw new IllegalStateException("Student does not exist. How can I delete it?");
        }
        studentRepository.deleteById(id);
    }

    // PUT
//    @Transactional
//    public void updateStudent(Student student){
//        Optional<Student> studentByID = studentRepository.findStudentByIdIs(student.getId());
//        if(!studentByID.isPresent()){
//            throw new IllegalStateException("Student does not exist so you cannot update the value");
//        }
//        studentRepository.save(student);
//    }

    // PUT
    @Transactional
    public void updateStudent(Long id, String name, String mail){
        Student newStud = studentRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException(
                        "student with id" + id + "does not exist!"
                ));
        if(name != null &&
        name.length() > 0 &&
        !Objects.equals(newStud.getName(), name)){
            newStud.setName(name);
        }

        if(mail != null &&
                mail.length() > 0 &&
                !Objects.equals(newStud.geteMail(), mail)){
            Optional<Student> studentOptional = studentRepository.findStudentByEMail(mail);
            if(studentOptional.isPresent()){
                throw new IllegalStateException("email taken");
            }
            newStud.seteMail(mail);
        }

        studentRepository.save(newStud);
    }
}
