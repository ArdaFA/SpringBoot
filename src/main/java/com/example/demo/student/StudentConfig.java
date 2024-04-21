package com.example.demo.student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class StudentConfig {

    @Bean
    CommandLineRunner commandLineRunner(StudentRepository repository){
        return args -> {
            Student arda = new Student(
                    1L,
                    "Arda",
                    "a@gmail.com",
                    LocalDate.of(2001, Month.MAY,16)
            );

            Student ceren = new Student(
                    "ceren",
                    "cer@gmail.com",
                    LocalDate.of(2003, Month.MAY,16)
            );

            repository.saveAll(
                    List.of(arda, ceren)
            );
        };
    }
}
