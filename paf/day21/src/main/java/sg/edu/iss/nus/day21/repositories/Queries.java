package sg.edu.iss.nus.day21.repositories;

public class Queries {

    // select book_id, title, authors, description from book2018 where book_id like "%c17062ea%";
    public static final String SQL_SELECT_BOOKS_BY_ID = """
        select book_id, title, authors, description, edition, format, pages, rating, rating_count, review_count, genres, image_url
            from book2018
            where book_id like ?
    """;

    //Find all books, sorted by book title, ascending order, alphabetical
    // group by title, uses aggregate fn, but group records with similar attributes
    // select title from book2018 where title like 'A%' group by title order by title asc;
    // where title like ?

    public static final String SQL_SELECT_BOOKS_BY_TITLE_ASCENDING = """
        select book_id, title, authors, description, edition, format, pages, rating, rating_count, review_count, genres, image_url
            from book2018
            order by title asc
            limit ? offset ?
    """;

    public static final String SQL_SELECT_BOOKS_BY_TITLE = """
    select book_id, title, authors, description, edition, format, pages, rating, rating_count, review_count, genres, image_url
        from book2018
    """;

    //Find all books with paperbacks with a rating greater than 4
    public static final String SQL_SELECT_BOOK_BY_FORMAT_AND_RATING = """
        select book_id, title, rating, rating_count
            from book2018
            where format = ? and rating > ?
    """; 

    // multiline """
    public static final String SQL_SELECT_BOOK_BY_TITLE_KEYWORD = """
        select book_id, title, authors, description, pages, rating, image_url
            from book2018
            where title like ?
            limit ? offset ?
    """;
    
}
