package sg.edu.nus.iss.d25client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

import sg.edu.nus.iss.d25client.repositories.MessagePoller;

@EnableAsync
@SpringBootApplication
public class D25ClientApplication implements CommandLineRunner {

	@Autowired
	private MessagePoller poller;

	public static void main(String[] args) {
		SpringApplication.run(D25ClientApplication.class, args);
	}

	//mvn clean spring-boot:run -Dspring-boot.run.arguments=acme
	//mvn clean spring-boot:run -Dspring-boot.run.arguments=mich
	@Override
	public void run(String... args) throws Exception {
		poller.start();
	}

	
}
