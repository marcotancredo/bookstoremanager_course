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
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.List;

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

    @ApiOperation(value = "List all books by a specific authenticated user")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Return all registered books")
    })
    List<BookResponseDTO> findAllByUser(AuthenticatedUser authenticatedUser);


    @ApiOperation(value = "Delete book operation")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Success book deleted"),
            @ApiResponse(code = 404, message = "Book not found error code!")
    })
    void deleteByIdAndUser(AuthenticatedUser authenticatedUser, Long bookId);

    @ApiOperation(value = "Book update operation")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Book by user successfully updated"),
            @ApiResponse(code = 404, message = "Book not found error code!"),
            @ApiResponse(code = 400, message = "Missing required fields, wrong field range value or book " +
                    "already registered on system")
    })
    BookResponseDTO updateByIdAndUser(AuthenticatedUser authenticatedUser, Long bookId, BookRequestDTO bookRequestDTO);
}
