package sg.edu.nus.iss.d28;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import sg.edu.nus.iss.d28.models.Game;

@SpringBootApplication
public class D28Application implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(D28Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// Game game = new Game(1, "test", 1, 1, 1);
		// System.out.println(game.toString());	
	}


}
