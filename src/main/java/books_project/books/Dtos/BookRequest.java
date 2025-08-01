package books_project.books.Dtos;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class BookRequest {

    @NotBlank(message = "Title must not be blank")
    private String title;

    @NotBlank(message = "Author must not be blank")
    private String author;

    @NotNull(message = "Publication year must not be null")
    @Min(value = 1000, message = "Publication year must be a valid year")
    @Max(value = 2025, message = "Publication year cannot be in the future")
    private Integer publicationYear;

    @NotBlank(message = "ISBN must not be blank")
    private String isbn;

    public BookRequest(String title, String author, Integer publicationYear, String isbn) {
        this.title = title;
        this.author = author;
        this.publicationYear = publicationYear;
        this.isbn = isbn;
    }

    public @NotBlank(message = "Title must not be blank") String getTitle() {
        return title;
    }

    public void setTitle(@NotBlank(message = "Title must not be blank") String title) {
        this.title = title;
    }

    public @NotBlank(message = "Author must not be blank") String getAuthor() {
        return author;
    }

    public void setAuthor(@NotBlank(message = "Author must not be blank") String author) {
        this.author = author;
    }

    public @NotNull(message = "Publication year must not be null") @Min(value = 1000, message = "Publication year must be a valid year") @Max(value = 2025, message = "Publication year cannot be in the future") Integer getPublicationYear() {
        return publicationYear;
    }

    public void setPublicationYear(@NotNull(message = "Publication year must not be null") @Min(value = 1000, message = "Publication year must be a valid year") @Max(value = 2025, message = "Publication year cannot be in the future") Integer publicationYear) {
        this.publicationYear = publicationYear;
    }

    public @NotBlank(message = "ISBN must not be blank") String getIsbn() {
        return isbn;
    }

    public void setIsbn(@NotBlank(message = "ISBN must not be blank") String isbn) {
        this.isbn = isbn;
    }

}
