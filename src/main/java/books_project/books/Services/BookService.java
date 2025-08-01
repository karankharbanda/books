package books_project.books.Services;

import books_project.books.Dtos.BookRequest;
import books_project.books.Dtos.BookResponse;
import books_project.books.Exceptions.ResourceNotFoundException;
import books_project.books.Repositories.BookRepo;
import books_project.books.models.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookService {

    @Autowired
    private BookRepo bookRepository;

    @Transactional
    public BookResponse createBook(BookRequest bookRequest) {
        if (bookRepository.existsByIsbn(bookRequest.getIsbn())) {
            throw new DataIntegrityViolationException("ISBN already exists: " + bookRequest.getIsbn());
        }
        Book book = new Book();
        mapRequestToBook(bookRequest, book);
        Book savedBook = bookRepository.save(book);
        return mapBookToResponse(savedBook);
    }

    @Transactional(readOnly = true)
    public List<BookResponse> getAllBooks() {
        return bookRepository.findAll().stream()
                .map(this::mapBookToResponse)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public BookResponse getBookById(Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book with ID " + id + " not found"));
        return mapBookToResponse(book);
    }

    @Transactional
    public BookResponse updateBook(Long id, BookRequest bookRequest) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book with ID " + id + " not found"));

        // Check if the new ISBN is already taken by another book
        bookRepository.findByIsbn(bookRequest.getIsbn()).ifPresent(existingBook -> {
            if (!existingBook.getId().equals(id)) {
                throw new DataIntegrityViolationException("ISBN already exists: " + bookRequest.getIsbn());
            }
        });

        mapRequestToBook(bookRequest, book);
        Book updatedBook = bookRepository.save(book);
        return mapBookToResponse(updatedBook);
    }

    @Transactional
    public void deleteBook(Long id) {
        if (!bookRepository.existsById(id)) {
            throw new ResourceNotFoundException("Book with ID " + id + " not found");
        }
        bookRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public List<BookResponse> searchBooksByTitle(String title) {
        return bookRepository.findByTitle(title).stream()
                .map(this::mapBookToResponse)
                .collect(Collectors.toList());
    }

    private BookResponse mapBookToResponse(Book book) {
        return new BookResponse(book.getId(), book.getTitle(), book.getAuthor(), book.getPublicationYear(), book.getIsbn());
    }

    private void mapRequestToBook(BookRequest request, Book book) {
        book.setTitle(request.getTitle());
        book.setAuthor(request.getAuthor());
        book.setPublicationYear(request.getPublicationYear());
        book.setIsbn(request.getIsbn());
    }
}
