package sg.edu.iss.nus.day21.models;

import jakarta.json.Json;
import jakarta.json.JsonObject;

public class Book {

    private String bookId; 
    private String title;
    private String authors;
    private String description;
    private String edition;
    private String format;
    private int page;
    private float rating;
    private int ratCount;
    private int reviewCount;
    private String genres;
    private String imageUrl;

    @Override
    public String toString() {
        return "Book [bookId=" + bookId + ", title=" + title + ", authors=" + authors + ", description=" + description
                + ", edition=" + edition + ", format=" + format + ", page=" + page + ", rating=" + rating
                + ", ratCount=" + ratCount + ", reviewCount=" + reviewCount + ", genres=" + genres + ", imageUrl="
                + imageUrl + "]";
    }

    public Book() {
    }

    public Book(String bookId, String title, String authors, String description) {
        this.bookId = bookId;
        this.title = title;
        this.authors = authors;
        this.description = description;
    }

     // select book_id, title, authors, description
    public Book(String bookId, String title, String authors, String description, String edition, String format, int page, float rating,
            int ratCount, int reviewCount, String genres, String imageUrl) {
        this.bookId = bookId;
        this.title = title;
        this.authors = authors;
        this.description = description;
        this.edition = edition;
        this.format = format;
        this.page = page;
        this.rating = rating;
        this.ratCount = ratCount;
        this.reviewCount = reviewCount;
        this.genres = genres;
        this.imageUrl = imageUrl;
    }

    public JsonObject toJSON() {
        return Json.createObjectBuilder()
            .add("bookId", getBookId())
            .add("title", getTitle())
            .add("authors", getAuthors())
            .add("description", getDescription())
            .add("edition", getEdition())
            .add("rating", getRating())
            .add("rating_count", getRatCount())
            .add("review_count", getReviewCount())
            .add("genres", getGenres())
            .add("image", getImageUrl())
            .build();
    }

    public String getBookId() { return bookId; }
    public void setBookId(String bookId) {  this.bookId = bookId; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getAuthors() { return authors; }
    public void setAuthors(String authors) { this.authors = authors; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public String getEdition() { return edition; }
    public void setEdition(String edition) { this.edition = edition; }
    public String getFormat() { return format; }
    public void setFormat(String format) { this.format = format; }
    public int getPage() { return page; }
    public void setPage(int page) { this.page = page; }
    public float getRating() { return rating; }
    public void setRating(float rating) { this.rating = rating; }
    public int getRatCount() { return ratCount; }
    public void setRatCount(int ratCount) { this.ratCount = ratCount; }
    public int getReviewCount() { return reviewCount; }
    public void setReviewCount(int reviewCount) { this.reviewCount = reviewCount; }
    public String getGenres() { return genres; }
    public void setGenres(String genres) { this.genres = genres; }
    public String getImageUrl() { return imageUrl; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }

   

   
    
}
