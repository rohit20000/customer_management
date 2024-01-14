package fi.haagahelia.course;

import java.util.Arrays;
import java.util.stream.Stream;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import fi.haagahelia.course.domain.Course;
import fi.haagahelia.course.domain.CourseRepository;
import fi.haagahelia.course.domain.Student;
import fi.haagahelia.course.domain.StudentRepository;
import fi.haagahelia.course.domain.User;
import fi.haagahelia.course.domain.UserRepository;

@SpringBootApplication
public class CrudbootApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(CrudbootApplication.class, args);
	}
	
	/**
	 * Save demo users, courses and students to H2 DB
	 * @param repository
	 * @return
	 */
	@Bean
	public CommandLineRunner demo(StudentRepository repository, CourseRepository crepository, UserRepository urepository) {
		return (args) -> {
			// save students
			repository.save(new Student("John", "Johnson","Street1","address1" ,"City1", "State1","gmail.com", "XXXXXX" ));
			repository.save(new Student("Mary", "Moore","Street2","address2" ,"City2", "State2","gmail.com", "XXXXXX" ));
			Stream.of("Programming Java", "Spring Boot basics", "Marketing 1", "Marketing 2").forEach(name -> {
				crepository.save(new Course(name));
			});

			// Create users with BCrypt encoded password (user/user, admin/admin)
			User user1 = new User("user", "$2a$06$3jYRJrg0ghaaypjZ/.g4SethoeA51ph3UD4kZi9oPkeMTpjKU5uo6", "USER");
			User user2 = new User("admin", "$2a$08$bCCcGjB03eulCWt3CY0AZew2rVzXFyouUolL5dkL/pBgFkUH9O4J2", "ADMIN");
			urepository.saveAll(Arrays.asList(user1, user2));
		};
	}
}
