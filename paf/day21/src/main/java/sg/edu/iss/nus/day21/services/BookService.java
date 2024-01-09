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
import sg.edu.iss.nus.day21.repositories.Queries;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepo;

    private Optional<Book> opt;

    private Logger logger = Logger.getLogger(BookService.class.getName());

    public Integer count() {
        List<Book> bookList = bookRepo.findBooksByTitle();

        if (bookList.isEmpty()) {
            logger.info("Invalid title");

        } else {
            logger.info("Total no. of books" + bookList.size());
        } return bookList.size();
    }

    public Optional<Book> findBooksById(String bookId) {
        opt = bookRepo.findBooksById(bookId);

        //returns true if present
        if (!opt.isPresent()) {
            logger.info("Invalid book id");
            return Optional.empty();
        } else {
            logger.info("Book id found" + opt.get());
            return opt;
        }
    }
    
    // entries can be null
    public List<Book> findBooksOrderByTitle(final int limit, final int offset) {

        List<Book> bookList = bookRepo.findBooksOrderByTitle(limit, offset);

        if (bookList.isEmpty()) {
            logger.info("Invalid title");
            return new LinkedList<>();

        } else {
            logger.info("Books found" + bookList);
            return bookList;
        } 
    }



    
}
