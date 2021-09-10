package com.nhatlong.springdatajpa;

import com.nhatlong.springdatajpa.entity.Student;
import com.nhatlong.springdatajpa.repository.StudentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringDataJpaApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringDataJpaApplication.class, args);
    }

    @Bean
    public CommandLineRunner runner(StudentRepository repository) {
        return (args -> {
            insertStudent(repository);
            System.out.println("[**] Insert: "+repository.findAll());
            updateStudent(repository);
            System.out.println("[**] Update: "+repository.findAll());
            deleteStudent(repository);
            System.out.println("[**] Delete: "+repository.findAll());
            insertStudent(repository);
            System.out.println("[**] Find Student by Last Name: "+repository.findStudentByLastNameContaining("Van A"));
        });
    }

    private void insertStudent(StudentRepository repository) {
        Student student = Student.builder()
                .firstName("Nguyen")
                .lastName("Van A")
                .email("nguyenvana@gmail.com")
                .build();
        repository.save(student);
    }

    private void updateStudent(StudentRepository repository) {
        Student student = Student.builder()
                .id(1L)
                .firstName("Nguyen")
                .lastName("Van B")
                .email("nguyenvana@gmail.com")
                .build();
        repository.save(student);
    }

    private void deleteStudent(StudentRepository repository) {
        repository.deleteById(1L);
    }
}
