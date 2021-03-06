package br.com.marcotancredo.bookstoremanager.model.users.controller;

import br.com.marcotancredo.bookstoremanager.model.users.dto.JwtRequest;
import br.com.marcotancredo.bookstoremanager.model.users.dto.JwtResponse;
import br.com.marcotancredo.bookstoremanager.model.users.dto.MessageDTO;
import br.com.marcotancredo.bookstoremanager.model.users.dto.UserDTO;
import br.com.marcotancredo.bookstoremanager.model.users.service.AuthenticationService;
import br.com.marcotancredo.bookstoremanager.model.users.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/users")
public class UserController implements UserControllerDocs {

    private UserService userService;

    private AuthenticationService authenticationService;

    @Autowired
    public UserController(UserService userService, AuthenticationService authenticationService) {
        this.userService = userService;
        this.authenticationService = authenticationService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MessageDTO create(@RequestBody @Valid UserDTO userToCreateDTO) {
        return userService.create(userToCreateDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        userService.delete(id);
    }

    @PutMapping("/{id}")
    public MessageDTO update(@PathVariable Long id, @RequestBody @Valid UserDTO userToUpdateDTO) {
        return userService.update(id, userToUpdateDTO);
    }

    @PostMapping(value = "/authenticate")
    public JwtResponse createAuthenticationToken(@RequestBody @Valid JwtRequest jwtRequest) {
        return authenticationService.createAuthenticationToken(jwtRequest);
    }
}
