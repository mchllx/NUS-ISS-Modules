package sg.edu.nus.iss.d27lecture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import sg.edu.nus.iss.d27lecture.repositories.PersonRepository;
import sg.edu.nus.iss.d27lecture.services.PersonService;

@SpringBootApplication
public class D27lectureApplication implements CommandLineRunner {

	@Autowired
	private PersonRepository personRepo;

	@Autowired
	private PersonService personSvc;

	public static void main(String[] args) {
		SpringApplication.run(D27lectureApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// personRepo.insertOne();
		// personRepo.deleteOne();
		// personRepo.deleteMany();
		// personRepo.insertMany("./data/persons.csv");
		// personSvc.readCSV("./data/persons.csv");
		// personRepo.updateOne();
		// personRepo.updateHobbyForOne();
		personRepo.upsertOne();
	}

}
