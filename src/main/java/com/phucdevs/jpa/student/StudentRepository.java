package com.phucdevs.jpa.student;


import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends PagingAndSortingRepository<Student, Long> {
    
    @Query("SELECT s FROM Student s WHERE s.email = :email")
    Optional<Student> findStudentByEmail(String email);
    
    @Query("SELECT s FROM Student s WHERE s.firstName = :firstName AND s.age >= :age")
    List<Student> selectStudentWhereFirstNameAndAgeGreaterOrEqual(
            String firstName, Integer age
    );
      
    
    @Query(
            value = "SELECT * FROM student WHERE first_name = :firstName AND age >= :age",
            nativeQuery = true
    )
    List<Student> selectStudentWhereFirstNameAndAgeGreaterOrEqualNative(
            @Param("firstName") String firstName, 
            @Param("age") Integer age      
    );
    
    @Transactional
    @Modifying
    @Query("DELETE FROM Student s where s.id = :id")
    int deleteStudentById(Long id);
}
