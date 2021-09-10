package com.nhatlong.springdatajpa.repository;

import com.nhatlong.springdatajpa.entity.Student;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface StudentRepository extends CrudRepository<Student,Long> {
    List<Student> findStudentByLastNameContaining(String str);
}
