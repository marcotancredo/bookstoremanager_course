package br.com.marcotancredo.bookstoremanager.model.users.service;

import br.com.marcotancredo.bookstoremanager.model.users.mapper.UserMapper;
import br.com.marcotancredo.bookstoremanager.model.users.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final static UserMapper userMapper = UserMapper.INSTANCE;

    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
}
