package sg.edu.nus.iss.d26workshop;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import sg.edu.nus.iss.d26workshop.models.Comment;
import sg.edu.nus.iss.d26workshop.models.Game;
import sg.edu.nus.iss.d26workshop.models.Games;
import sg.edu.nus.iss.d26workshop.repositories.GameRepository;
import sg.edu.nus.iss.d26workshop.services.GameService;

@SpringBootApplication
public class D26WorkshopApplication implements CommandLineRunner {

	// @Autowired
	// private GameRepository gameRepo;

	@Autowired
	private GameService gameSvc;

	public static void main(String[] args) {
		SpringApplication.run(D26WorkshopApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// Integer gid, String name, Integer year, Integer ranking, Integer usersRated, String url, String image;
		// System.out.println(new Game(0, "test", 0000, 0, 0, "", "").toString());
		
		// Game game = new Game(0, "test", 0000, 0, 0, null, "");
		// System.out.println(game.toJSON().toString());

		// String user, String text, Integer rating, Integer gid;
		// Comment comment = new Comment("user", null, 0, 0);
		// System.out.println(comment.toJSON().toString());	

		// List<Game> games, Integer limit, Integer offset, Integer total, DateTime timestamp;
		// List<Game> games = new LinkedList<>();
		// 	System.out.println(new Games(games, 1, 1, 10, DateTime.now()));
		
		//get
		// System.out.println("Search results" + gameRepo.search(5, 10));

		//get, sort ranking
		// System.out.println("Search results" + gameRepo.searchByRanking());

		//get by gid
		// System.out.println("Search results" + gameRepo.getGameById(1));

		//get summary
		// System.out.println("Game results" + gameSvc.getSummary(10, 0));


	}

}
