package sg.edu.iss.nus.day21.services;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Service;

import sg.edu.iss.nus.day21.models.Book;
import sg.edu.iss.nus.day21.repositories.BookRepository;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepo;

    private List<Book> bookList;
    private Optional<Book> opt;

    private Logger logger = Logger.getLogger(BookService.class.getName());

    public Optional<Book> finalBooksById(final String bookId, final int limit, final int offset) {
        opt = bookRepo.findBooksById(bookId, limit, offset);

        //returns true if present
        if (!opt.isPresent()) {
            logger.info("No books found");
            return Optional.empty();
        } else {
            logger.info("Books found" + opt.get());
            return opt;
        }
    }
    
    // entries can be null
    public List<Book> findBooksOrderByTitle(final int limit, final int offset) {

        List<Book> bookList = bookRepo.findBooksOrderByTitle(limit, offset);

        if (bookList.isEmpty()) {
            logger.info("No books found");
            return new LinkedList<>();

        } else {
            logger.info("Books found" + bookList);
            return bookList;
        } 
    }



    
}
