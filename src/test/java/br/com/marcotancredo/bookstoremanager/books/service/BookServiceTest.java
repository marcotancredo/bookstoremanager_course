package br.com.marcotancredo.bookstoremanager.books.service;

import br.com.marcotancredo.bookstoremanager.books.builder.BookRequestDTOBuilder;
import br.com.marcotancredo.bookstoremanager.books.builder.BookResponseDTOBuilder;
import br.com.marcotancredo.bookstoremanager.model.authors.service.AuthorService;
import br.com.marcotancredo.bookstoremanager.model.books.mapper.BookMapper;
import br.com.marcotancredo.bookstoremanager.model.books.repository.BookRepository;
import br.com.marcotancredo.bookstoremanager.model.books.service.BookService;
import br.com.marcotancredo.bookstoremanager.model.publishers.service.PublisherService;
import br.com.marcotancredo.bookstoremanager.model.users.dto.AuthenticatedUser;
import br.com.marcotancredo.bookstoremanager.model.users.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class BookServiceTest {

    private final BookMapper bookMapper = BookMapper.INSTANCE;

    @Mock
    private BookRepository bookRepository;

    @Mock
    private UserService userService;

    @Mock
    private AuthorService authorService;

    @Mock
    private PublisherService publisherService;

    @InjectMocks
    private BookService bookService;

    private BookRequestDTOBuilder bookRequestDTOBuilder;

    private BookResponseDTOBuilder bookResponseDTOBuilder;

    private AuthenticatedUser authenticatedUser;

    @BeforeEach
    void setUp() {
        bookRequestDTOBuilder = BookRequestDTOBuilder.builder().build();
        bookResponseDTOBuilder = BookResponseDTOBuilder.builder().build();
        authenticatedUser = new AuthenticatedUser("marcotancredo", "123456", "ADMIN");
    }
}
