package com.phucdevs.jpa;

import com.github.javafaker.Faker;
import com.phucdevs.jpa.student.Student;
import com.phucdevs.jpa.student.StudentIdCardRepository;
import com.phucdevs.jpa.student.StudentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class JpaApplication {

	public static void main(String[] args) {
		SpringApplication.run(JpaApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(
			StudentRepository studentRepository,
			StudentIdCardRepository studentIdCardRepository) {
		return args -> {
			Faker faker = new Faker();

			String firstName = faker.name().firstName();
			String lastName = faker.name().lastName();
			String email = String.format("%s.%s@amigoscode.edu", firstName, lastName);
			Student student = Student.builder()
						.firstName(firstName)
						.lastName(lastName)
						.email(email)
						.age(faker.number().numberBetween(17, 55))
					.build();
		};
	}
}
