package br.com.marcotancredo.bookstoremanager.model.users.controller;

import br.com.marcotancredo.bookstoremanager.model.users.dto.MessageDTO;
import br.com.marcotancredo.bookstoremanager.model.users.dto.UserDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api("System users management")
public interface UserControllerDocs {

    @ApiOperation(value = "User creation operation")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Success user creation"),
            @ApiResponse(code = 400, message = "Missing required fields, wrong filed value or user already registered!")
    })
    MessageDTO create(UserDTO userToCreateDTO);

    /* @ApiOperation(value = "Find publisher by id operation")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success publisher found"),
            @ApiResponse(code = 404, message = "Publisher not found error!")
    })
    PublisherDTO findById(Long id);

    @ApiOperation(value = "List all registered publishers")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Return all registered publishers")
    })
    List<PublisherDTO> findAll();


    @ApiOperation(value = "Delete publisher by id operation")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Success publisher deleted"),
            @ApiResponse(code = 404, message = "Publisher not found error code!")
    })
    void delete(Long id); */
}
