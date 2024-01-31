package sg.edu.nus.iss.d26task;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import sg.edu.nus.iss.d26task.models.Game;
import sg.edu.nus.iss.d26task.repositories.GameRepository;

@SpringBootApplication
public class D26TaskApplication implements CommandLineRunner{

	@Autowired
	private GameRepository gameRepo;

	public static void main(String[] args) {
		SpringApplication.run(D26TaskApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// List<Game> games = gameRepo.findGamesbyName("detective");

		// for (Game g: games) {
		// System.out.println(games);
		// }

		// System.out.printf("count: %d\n", games.size());
		}

}
