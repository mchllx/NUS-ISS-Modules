package sg.edu.iss.nus.day21.repositories;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import sg.edu.iss.nus.day21.Utils;
import sg.edu.iss.nus.day21.models.Book;

@Repository
public class BookRepository {

    @Autowired
    private JdbcTemplate template;

    private Logger logger = Logger.getLogger(BookRepository.class.getName());

    List<Book> bookList;

    Optional<Book> opt;

    public Optional<Book> findBooksById(String bookId, int limit, int offset) {
        SqlRowSet rs = template.queryForRowSet(Queries.SQL_SELECT_BOOKS_BY_ID, bookId, limit, offset);

        //if next row from column does not exist
        if (!rs.next()) {
            //null values are valid in this table
            logger.info("No books found");
            return Optional.empty();
        } else {
            opt = Optional.of(Utils.toBook(rs));
            logger.info("\n\nQuery completed\n" + opt);
            return opt;
        } 

    }

    // select book_id, title, authors, description, edition, format, pages, rating, rating_count, review_count, genres, image_url
    // select title from book2018 group by title order by title asc;
    public List<Book> findBooksOrderByTitle(int limit, int offset) {

        // String toSearch = A + "%";
        // System.out.println(toSearch); 

        SqlRowSet rs = template.queryForRowSet(Queries.SQL_SELECT_BOOKS_BY_TITLE_ASCENDING
        , limit, offset);
       
        int count = 0;

        while (rs.next()) {

            bookList = new LinkedList<>();
            bookList.add(Utils.toBook(rs));
            count = count+1;
            
            // String bookId = rs.getString("book_Id");
            // // c17062ea
            // String title = rs.getString("title");
            // // A Breath After Drowning
            // String authors = rs.getString("authors");
            // // Alice Blanchard
            // String description = rs.getString("description");
            // //The stunning new psychological thriller...
            // String edition = rs.getString("edition");
            // //null
            // String format = rs.getString("format");
            // // Kindle Edition
            // int pages = rs.getInt("pages");
            // // 0 
            // float rating = rs.getFloat("rating");
            // // 3.77
            // int ratCount = rs.getInt("rating_count");
            // // 638
            // int reviewCount = rs.getInt("review_count");
            // // 138
            // String genres = rs.getString("genres");
            // // Thriller|Mystery|Suspense|Fiction
            // String imageUrl = rs.getString("image_url");
            // // https://images.gr-assets.com/books/1513224961l/34235951.jpg

            // bookList.add(book);

            // System.out.printf("Title: %s\n", title);
            // Check if sorted, A Breath After Drowning, A Brotherhood of Spies:, A Brush with Shadows 

            // System.out.printf(
            //     "%s,%s,%s,%s,%s,%s,%d,%.3f,%d,%d,%s,%s\n", bookId, title, authors, description, edition,
            //     format, pages, rating, ratCount, reviewCount, genres, imageUrl);

            // System.out.println("----------------Book:-------------" + book.toString());
            // // 1st record, A Breath After Drowning   , Last record, Aunt Dimity and the King's Ransom       
            
            // System.out.print("Count:" + count);  
            //check if all 92 rows printed  
          
            System.out.println("----------------Book List:-------------" + bookList.toString());

        }
        
        System.out.println("\n\nQuery completed");
        return bookList;
    } 

    //write a method for returning paperback books with ratings greater than 4
    public void findBooksByRating(String format, float rating) {
        //select book_id, title, rating, rating_count from book2018 where rating > 4;

        SqlRowSet rs = template.queryForRowSet(Queries.SQL_SELECT_BOOK_BY_FORMAT_AND_RATING
            , format, rating);

        while (rs.next()) {
            String bookId = rs.getString("book_id");
            String title = rs.getString("title");
            // | c170617d | Zero Day   ... | c1706320 | Εξύψωση      
            float _rating = rs.getFloat("rating");
            int ratCount = rs.getInt("rating_count");

            System.out.printf("%s,%s, %.3f,%d\n", bookId, title, _rating, ratCount);
        }
        System.out.println("\n\nQuery completed");
    }

    //write a method, pass in params
    public void findBooksByTitle(String keyword) {

        // not SQL injection, because this value will be replaced by query
        String toSearch = "%" + keyword + "%";

        // SQL query, String key, read left -> right
        // throws an SQLRowSet exception if there is a syntax error
        SqlRowSet rs = template.queryForRowSet(Queries.SQL_SELECT_BOOK_BY_TITLE
            , toSearch, 10, 0);

        while (rs.next()) {
            String bookId = rs.getString("book_id");
            // System.out.println("Book Id:" + bookId);
            // Book Id:c170619f

            String title = rs.getString("title");
            // System.out.println("Title:" + title);
            // Murder in the One Percent

            int pages = rs.getInt("pages");
            // System.out.println("Pages:" + pages);

            float rating = rs.getFloat("rating");
            // System.out.println("Rating:" + rating);
            // 4.38

            String imageUrl = rs.getString("image_url");
            // System.out.println("Image Url:" + image);
            // https://images.gr-assets.com/books/1518843920l/38602787.jpg

            System.out.printf("%s, %s, %d, %.3f, %s\n", bookId, title, pages, rating, imageUrl);
        } 

        System.out.println("\n\nQuery completed");

    }
    
}
