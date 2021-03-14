package br.com.marcotancredo.bookstoremanager.model.authors.controller;

import br.com.marcotancredo.bookstoremanager.model.authors.dto.AuthorDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

@Api("Authors management")
public interface AuthorControllerDocs {

    @ApiOperation(value = "Author creation operation")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Success author creation"),
            @ApiResponse(code = 400, message = "Missing required fields, wrong filed value or author already registered!")
    })
    AuthorDTO create(AuthorDTO authorDTO);
}
