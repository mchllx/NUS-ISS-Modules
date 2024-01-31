package sg.edu.nus.iss.d25workshop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

import sg.edu.nus.iss.d25workshop.repository.MessagePoller;

@SpringBootApplication
@EnableAsync
public class D25workshopApplication implements CommandLineRunner {

	@Autowired
	private MessagePoller poller;

	public static void main(String[] args) {
		SpringApplication.run(D25workshopApplication.class, args);
	}

	//... = varargs
	@Override
	public void run(String... args) throws Exception {
		poller.start();
	}
	
}
