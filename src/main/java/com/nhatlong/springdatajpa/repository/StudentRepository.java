package com.nhatlong.springdatajpa.repository;

import com.nhatlong.springdatajpa.entity.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StudentRepository extends CrudRepository<Student,Long> {
//    DEFAULT QUERY
    List<Student> findStudentByLastNameContaining(String str);

//    -------------- Native Query ---------------------
//    SELECT
    @Query(value = "SELECT * FROM student WHERE first_name = :firstName",
            nativeQuery = true)
    List<Student> getStudentByFirstName(@Param("firstName") String firstName);
//    DELETE
    @Modifying
    @Query(value = "DELETE FROM student s WHERE s.last_name = :lastName",
            nativeQuery = true)
    void deleteStudentByLastName(@Param("lastName") String lastName);
//    UPDATE
    @Modifying
    @Query(value = "UPDATE student SET first_name= :firstName, last_name= :lastName WHERE email= :email",
            nativeQuery = true)
    void updateStudentByEmail(String firstName, String lastName, String email);

//    -------------- JPQL Query---------------------
//    SELECT
    @Query("FROM Student WHERE firstName = ?1")
    List<Student> getStudentByFirstNameJPQL(@Param("firstName") String firstName);
//    DELETE
    @Modifying
    @Query("DELETE FROM Student WHERE lastName = :lastName")
    int deleteStudentByLastNameJPQL(@Param("lastName") String lastName);
//    UPDATE
    @Modifying
    @Query("UPDATE Student S SET S.firstName = :firstName, S.lastName = :lastName WHERE S.email = :email")
    int updateStudentByEmailJPQL(@Param("firstName") String firstName, @Param("lastName") String lastName, @Param("email") String email);

//    -------------- SORT ---------------------
//    NATIVE

//    JPQL
    @Query("SELECT Student FROM Student ")
    List<Student> findAllStudent(Sort sort);

//    -------------- Pagination ---------------------
//    NATIVE
    @Query(value = "SELECT * FROM student S WHERE S.first_name = ?1 ",
            countQuery = "SELECT COUNT(id) FROM Student S WHERE S.first_name = ?1",
            nativeQuery = true)
    Page<Student> getStudentByFirstName(String fistName, Pageable pageable);
//    JPQL
    @Query(value = "SELECT Student FROM Student WHERE firstName = ?1",
            countQuery = "SELECT COUNT(id) FROM Student WHERE firstName = ?1")
    Page<Student> getStudentByFirstNameJPQL(String fistName, Pageable pageable);
}
