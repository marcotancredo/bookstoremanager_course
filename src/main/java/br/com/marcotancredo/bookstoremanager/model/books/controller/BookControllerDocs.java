package br.com.marcotancredo.bookstoremanager.model.books.controller;

import br.com.marcotancredo.bookstoremanager.model.books.dto.BookRequestDTO;
import br.com.marcotancredo.bookstoremanager.model.books.dto.BookResponseDTO;
import br.com.marcotancredo.bookstoremanager.model.users.dto.AuthenticatedUser;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;

@Api("Books module management")
public interface BookControllerDocs {

    @ApiOperation(value = "Book creation operation")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Success book creation"),
            @ApiResponse(code = 400, message = "Missing required fields, wrong filed value or book already registered!")
    })
    BookResponseDTO create(AuthenticatedUser authenticatedUser, BookRequestDTO bookRequestDTO);

    @ApiOperation(value = "Find book by id operation")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success book found"),
            @ApiResponse(code = 404, message = "Book not found error code!")
    })
    BookResponseDTO findByIdAndUser(
            AuthenticatedUser authenticatedUser,
            Long bookId);

    /*
    @ApiOperation(value = "List all registered authors")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Return all registered authors")
    })
    List<AuthorDTO> findAll();

    @ApiOperation(value = "Delete author by id operation")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Success author deleted"),
            @ApiResponse(code = 404, message = "Author not found error code!")
    })
    void delete(Long id); */
}
