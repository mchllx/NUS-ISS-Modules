package sg.edu.iss.nus.day21;

import org.springframework.jdbc.support.rowset.SqlRowSet;

import sg.edu.iss.nus.day21.models.Book;

public class Utils {

    // select book_id, title, authors, description
    public static Book toBookSummary(SqlRowSet rs) {
        Book book = new Book(
            "0", "untitled", "unpublished", "n/a",
            "n/a", "n/a", 0, 0, 0, 0, "n/a", "n/a");
        book.setBookId(rs.getString("book_Id"));
        book.setTitle(rs.getString("title"));
        book.setAuthors(rs.getString("authors"));
        book.setDescription(rs.getString("description"));

        return book;
    }

    public static Book toBook(SqlRowSet rs) {
        Book book = new Book(
            "0", "untitled", "unpublished", "n/a",
            "n/a", "n/a", 0, 0, 0, 0, "n/a", "n/a");
        book.setBookId(rs.getString("book_id"));
        book.setTitle(rs.getString("title"));
        book.setAuthors(rs.getString("authors"));
        book.setDescription(rs.getString("description"));
        book.setEdition(rs.getString("edition"));
        book.setFormat(rs.getString("format"));
        book.setPage(rs.getInt("pages"));
        book.setRating(rs.getFloat("rating"));
        book.setRatCount(rs.getInt("rating_count"));
        book.setReviewCount(rs.getInt("review_count"));
        book.setGenres(rs.getString("genres"));
        book.setImageUrl(rs.getString("image_url"));

        return book;
    }
    
}
