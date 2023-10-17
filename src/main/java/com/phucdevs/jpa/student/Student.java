package com.phucdevs.jpa.student;

import com.phucdevs.jpa.book.Book;
import com.phucdevs.jpa.enrolment.Enrolment;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Entity
@Table(
        name = "student",
        uniqueConstraints = {
            @UniqueConstraint(name = "student_email_unique", columnNames = "email")
        }
)
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class Student {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    Long id;
    
    @Column(name = "first_name", nullable = false, columnDefinition = "TEXT")
    String firstName;

    @Column(name = "last_name", nullable = false, columnDefinition = "TEXT")
    String lastName;

    @Column(name = "email", nullable = false, columnDefinition = "TEXT")
    String email;
    
    @Column(name = "age")
    Integer age;
    
    @OneToOne(
            mappedBy = "student",
            orphanRemoval = true,
            cascade = { CascadeType.PERSIST, CascadeType.REMOVE }
    )
    StudentIdCard studentIdCard;
    
    
    @OneToMany(
            mappedBy = "student",
            orphanRemoval = true,
            cascade = { CascadeType.PERSIST, CascadeType.REMOVE },
            fetch = FetchType.LAZY
    )
    List<Book> books;
    
    @OneToMany(
            mappedBy = "student",
            cascade = { CascadeType.PERSIST, CascadeType.REMOVE }
    )
    List<Enrolment> enrolments;

    public void addBook(Book book) {
        if (!this.books.contains(book)) {
            this.books.add(book);
            book.setStudent(this);
        }
    }

    public void removeBook(Book book) {
        if (this.books.contains(book)) {
            this.books.remove(book);
            book.setStudent(null);
        }
    }

    public void addEnrolment(Enrolment enrolment) {
        if (!enrolments.contains(enrolment)) {
            enrolments.add(enrolment);
        }
    }

    public void removeEnrolment(Enrolment enrolment) {
        enrolments.remove(enrolment);
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", age=" + age +
                ", studentIdCard=" + studentIdCard +
                ", books=" + books +
                ", enrolments=" + enrolments +
                '}';
    }
}
