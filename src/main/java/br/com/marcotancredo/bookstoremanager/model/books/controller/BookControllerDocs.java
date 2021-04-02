package br.com.marcotancredo.bookstoremanager.model.books.controller;

import io.swagger.annotations.Api;

@Api("Books management")
public interface BookControllerDocs {

    /*@ApiOperation(value = "Author creation operation")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Success author creation"),
            @ApiResponse(code = 400, message = "Missing required fields, wrong filed value or author already registered!")
    })
    AuthorDTO create(AuthorDTO authorDTO);

    @ApiOperation(value = "Find author by id operation")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success author found"),
            @ApiResponse(code = 404, message = "Author not found error code!")
    })
    AuthorDTO findById(Long id);

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
