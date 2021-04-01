package br.com.marcotancredo.bookstoremanager.model.users.controller;

import br.com.marcotancredo.bookstoremanager.model.users.dto.JwtRequest;
import br.com.marcotancredo.bookstoremanager.model.users.dto.JwtResponse;
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

    @ApiOperation(value = "User publisher by id operation")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Success user deleted"),
            @ApiResponse(code = 404, message = "User not found error!")
    })
    void delete(Long id);

    @ApiOperation(value = "User update operation")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success user updated"),
            @ApiResponse(code = 400, message = "Missing required fields, wrong filed value or user already registered!")
    })
    MessageDTO update(Long id, UserDTO userToUpdateDTO);

    @ApiOperation(value = "User authentication operation")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success user authenticated"),
            @ApiResponse(code = 404, message = "User not found error!")
    })
    JwtResponse createAuthenticationToken(JwtRequest jwtRequest);
}
