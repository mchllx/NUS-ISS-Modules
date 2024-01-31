package sg.edu.nus.iss.d27lecture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import sg.edu.nus.iss.d27lecture.repositories.PersonRepository;

@SpringBootApplication
public class D27lectureApplication implements CommandLineRunner {

	@Autowired
	private PersonRepository personRepo;

	public static void main(String[] args) {
		SpringApplication.run(D27lectureApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// personRepo.insertOne();
		// personRepo.insertMany();
		personRepo.deleteOne();
		// personRepo.deleteMany();
	}

}
