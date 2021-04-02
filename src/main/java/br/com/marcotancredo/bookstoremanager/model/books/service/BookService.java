package br.com.marcotancredo.bookstoremanager.model.books.service;

import br.com.marcotancredo.bookstoremanager.model.authors.entity.Author;
import br.com.marcotancredo.bookstoremanager.model.authors.service.AuthorService;
import br.com.marcotancredo.bookstoremanager.model.books.dto.BookRequestDTO;
import br.com.marcotancredo.bookstoremanager.model.books.dto.BookResponseDTO;
import br.com.marcotancredo.bookstoremanager.model.books.entity.Book;
import br.com.marcotancredo.bookstoremanager.model.books.exception.BookAlreadyExistsException;
import br.com.marcotancredo.bookstoremanager.model.books.mapper.BookMapper;
import br.com.marcotancredo.bookstoremanager.model.books.repository.BookRepository;
import br.com.marcotancredo.bookstoremanager.model.publishers.entity.Publisher;
import br.com.marcotancredo.bookstoremanager.model.publishers.service.PublisherService;
import br.com.marcotancredo.bookstoremanager.model.users.dto.AuthenticatedUser;
import br.com.marcotancredo.bookstoremanager.model.users.entity.User;
import br.com.marcotancredo.bookstoremanager.model.users.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class BookService {

    private final BookMapper bookMapper = BookMapper.INSTANCE;

    private BookRepository bookRepository;

    private UserService userService;

    private AuthorService authorService;

    private PublisherService publisherService;

    public BookResponseDTO create(AuthenticatedUser authenticatedUser, BookRequestDTO bookRequestDTO){
        User foundAuthenticatedUser =  userService.verifyAndGetIfExists(authenticatedUser.getUsername());
        verifyIfBookIsAlreadyRegistered(bookRequestDTO, foundAuthenticatedUser);

        Author foundAuthor = authorService.verifyAndGetIfExists(bookRequestDTO.getAuthorId());
        Publisher foundPublisher = publisherService.verifyAndGetIfExists(bookRequestDTO.getPublisherId());

        Book bookToSave = bookMapper.toModel(bookRequestDTO);
        bookToSave.setUser(foundAuthenticatedUser);
        bookToSave.setAuthor(foundAuthor);
        bookToSave.setPublisher(foundPublisher);

        Book savedBook = bookRepository.save(bookToSave);

        return bookMapper.toDTO(savedBook);
    }

    private void verifyIfBookIsAlreadyRegistered(BookRequestDTO bookRequestDTO, User foundUser) {
        bookRepository.findByNameAndIsbnAndUser(bookRequestDTO.getName(), bookRequestDTO.getIsbn(), foundUser)
                    .ifPresent(duplicatedBook -> {
                        throw new BookAlreadyExistsException(bookRequestDTO.getName(), bookRequestDTO.getIsbn(), foundUser.getUsername());
                    });
    }
}
