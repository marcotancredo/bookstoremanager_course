package br.com.marcotancredo.bookstoremanager.users.service;


import br.com.marcotancredo.bookstoremanager.model.users.mapper.UserMapper;
import br.com.marcotancredo.bookstoremanager.model.users.repository.UserRepository;
import br.com.marcotancredo.bookstoremanager.model.users.service.UserService;
import br.com.marcotancredo.bookstoremanager.users.builder.UserDTOBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    private final UserMapper userMapper = UserMapper.INSTANCE;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    private UserDTOBuilder userDTOBuilder;

    @BeforeEach
    void setUp() {
        userDTOBuilder = UserDTOBuilder.builder().build();
    }
}
