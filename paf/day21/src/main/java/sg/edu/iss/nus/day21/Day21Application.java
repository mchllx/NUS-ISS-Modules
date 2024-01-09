package sg.edu.iss.nus.day21;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import sg.edu.iss.nus.day21.models.Book;
import sg.edu.iss.nus.day21.repositories.BookRepository;

@SpringBootApplication
public class Day21Application implements CommandLineRunner{

	@Autowired
	private BookRepository bookRepo;

	public static void main(String[] args) {
		SpringApplication.run(Day21Application.class, args);
	}

	@Override
	public void run(String... args) {
		// System.out.println("---------------------------------------");
		// bookRepo.findBooksByTitle("murder");

		// System.out.println("---------------------------------------");
		// bookRepo.findBooksByTitle("akldjlsjd");

		// System.out.println("---------------------------------------");
		// bookRepo.findBooksByRating("paperback", 4);

		System.out.println("---------------------------------------");
		List<Book> bookList = bookRepo.findBooksOrderByTitle(10, 0);

		System.out.println("---------------------------------------");
		Optional<Book> opt = bookRepo.findBooksById("c17062ea", 10, 0);
	}

}
