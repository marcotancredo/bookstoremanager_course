package br.com.marcotancredo.bookstoremanager.users.service;

import br.com.marcotancredo.bookstoremanager.model.users.dto.JwtRequest;
import br.com.marcotancredo.bookstoremanager.model.users.dto.JwtResponse;
import br.com.marcotancredo.bookstoremanager.model.users.dto.UserDTO;
import br.com.marcotancredo.bookstoremanager.model.users.entity.User;
import br.com.marcotancredo.bookstoremanager.model.users.mapper.UserMapper;
import br.com.marcotancredo.bookstoremanager.model.users.repository.UserRepository;
import br.com.marcotancredo.bookstoremanager.model.users.service.AuthenticationService;
import br.com.marcotancredo.bookstoremanager.model.users.service.JwtTokenManager;
import br.com.marcotancredo.bookstoremanager.users.builder.JwTRequestBuilder;
import br.com.marcotancredo.bookstoremanager.users.builder.UserDTOBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AuthenticationServiceTest {

    private final UserMapper userMapper = UserMapper.INSTANCE;

    @Mock
    private UserRepository userRepository;

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private JwtTokenManager jwtTokenManager;

    @InjectMocks
    private AuthenticationService authenticationService;

    private UserDTOBuilder userDTOBuilder;

    private JwTRequestBuilder jwTRequestBuilder;

    @BeforeEach
    void setUp(){
        userDTOBuilder = UserDTOBuilder.builder().build();
        jwTRequestBuilder = JwTRequestBuilder.builder().build();
    }

    @Test
    void whenUsernameAndPasswordIsInformedThenATokenShouldBeGenerated() {
        JwtRequest jwtRequest = jwTRequestBuilder.buildJwtRequest();
        UserDTO expectedFoundUserDTO = userDTOBuilder.builderUserDTO();
        User expectedFoundUser = userMapper.toModel(expectedFoundUserDTO);
        String expectedGeneratedToken = "fakeToken";

        when(userRepository.findByUsername(jwtRequest.getUsername())).thenReturn(Optional.of(expectedFoundUser));
        when(jwtTokenManager.generateToken(any(UserDetails.class))).thenReturn(expectedGeneratedToken);

        JwtResponse generatedTokenResponse = authenticationService.createAuthenticationToken(jwtRequest);

        assertThat(generatedTokenResponse.getJwtToken(), is(equalTo(expectedGeneratedToken)));

    }

    @Test
    void whenUsernameIsInformedThenUserShouldBeReturned() {
        UserDTO expectedFoundUserDTO = userDTOBuilder.builderUserDTO();
        User expectedFoundUser = userMapper.toModel(expectedFoundUserDTO);
        SimpleGrantedAuthority expectedUserRole = new SimpleGrantedAuthority("ROLE_" + expectedFoundUserDTO.getRole().getDescription());
        String expectedUsername = expectedFoundUserDTO.getUsername();


        when(userRepository.findByUsername(expectedUsername)).thenReturn(Optional.of(expectedFoundUser));
        UserDetails userDetails = authenticationService.loadUserByUsername(expectedUsername);

        assertThat(userDetails.getUsername(), is(equalTo(expectedUsername)));
        assertThat(userDetails.getPassword(), is(equalTo(expectedFoundUser.getPassword())));
        assertTrue(userDetails.getAuthorities().contains(expectedUserRole));
    }

    @Test
    void whenInvalidUsernameIsInformedThenAnExceptionShouldBeThrown() {
        UserDTO expectedFoundUserDTO = userDTOBuilder.builderUserDTO();
        String expectedUsername = expectedFoundUserDTO.getUsername();

        when(userRepository.findByUsername(expectedUsername)).thenReturn(Optional.empty());
        assertThrows(UsernameNotFoundException.class, () -> authenticationService.loadUserByUsername(expectedUsername));
    }
}
