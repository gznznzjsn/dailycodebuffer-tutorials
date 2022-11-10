package com.gznznzjsn.springdatajpatutorial.repository;

import com.gznznzjsn.springdatajpatutorial.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    List<Student> findStudentByFirstName(String firstName);

    List<Student> findStudentByFirstNameContaining(String partOfFirstName);

    List<Student> findStudentByLastNameNotNull();

    List<Student> findStudentByGuardianName(String guardianName);

    Student findStudentByFirstNameAndLastName(String firstName, String lastName);

    //JPQL
    @Query("select s from Student s where s.emailId = ?1")
    Student getStudentByEmailAddress(String emailAddress);

    //JPQL
    @Query("select s.firstName from Student s where s.emailId = ?1")
    String getStudentFirstNameByEmailAddress(String emailAddress);

    //native
    @Query(
            value = "select * from tbl_student s where s.email_address = ?1",
            nativeQuery = true

    )
    Student getStudentByEmailAddressNative(String emailAddress);

    //native
    @Query(
            value = "select * from tbl_student s where s.email_address = :emailId",
            nativeQuery = true

    )
    Student getStudentByEmailAddressNativeWithParam(@Param("emailId") String emailAddress);

    @Modifying
    @Transactional
    @Query(
            value = "update tbl_student s set s.first_name = :firstName where s.email_address = :emailId",
            nativeQuery = true
    )
    int updateStudentNameByEmailId(@Param("firstName") String firstName, @Param("emailId") String emailId);
}
