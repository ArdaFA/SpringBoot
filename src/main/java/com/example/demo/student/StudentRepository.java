package com.example.demo.student;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    // SELECT * FROM student WHERE email = ?
    @Query("SELECT s FROM Student s WHERE s.eMail = ?1")
    Optional<Student> findStudentByEMail(String email);

    @Query("SELECT s FROM Student s WHERE s.id = ?1")
    Optional<Student> findStudentByIdIs(Long id);

}
