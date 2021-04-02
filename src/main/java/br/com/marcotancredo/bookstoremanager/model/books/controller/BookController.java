package br.com.marcotancredo.bookstoremanager.model.books.controller;

import br.com.marcotancredo.bookstoremanager.model.books.dto.BookRequestDTO;
import br.com.marcotancredo.bookstoremanager.model.books.dto.BookResponseDTO;
import br.com.marcotancredo.bookstoremanager.model.books.service.BookService;
import br.com.marcotancredo.bookstoremanager.model.users.dto.AuthenticatedUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/books")
public class BookController {

    private BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BookResponseDTO create(
            @AuthenticationPrincipal AuthenticatedUser authenticatedUser,
            @RequestBody @Valid BookRequestDTO bookRequestDTO) {
        return bookService.create(authenticatedUser, bookRequestDTO);
    }
}
