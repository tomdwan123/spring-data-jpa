package com.phucdevs.jpa.course;

import com.phucdevs.jpa.enrolment.Enrolment;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Entity
@Table(name = "course")
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Course {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    Long id;
    
    @Column(
            name = "name",
            nullable = false,
            columnDefinition = "TEXT"
    )
    String name;

    @Column(
            name = "department",
            nullable = false,
            columnDefinition = "TEXT"
    )
    String department;
    
    @OneToMany(
            cascade = { CascadeType.PERSIST, CascadeType.REMOVE },
            mappedBy = "course"
    )
    List<Enrolment> enrolments;
    
    public void addEnrolment(Enrolment enrolment) {
        if (!enrolments.contains(enrolment)) {
            enrolments.add(enrolment);
        }
    }
    
    public void removeEnrolment(Enrolment enrolment) {
        enrolments.remove(enrolment);
    }
}
