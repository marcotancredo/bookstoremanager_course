package br.com.marcotancredo.bookstoremanager.model.users.service;

import br.com.marcotancredo.bookstoremanager.model.users.dto.MessageDTO;
import br.com.marcotancredo.bookstoremanager.model.users.dto.UserDTO;
import br.com.marcotancredo.bookstoremanager.model.users.entity.User;
import br.com.marcotancredo.bookstoremanager.model.users.exception.UserAlreadyExistsException;
import br.com.marcotancredo.bookstoremanager.model.users.exception.UserNotFoundException;
import br.com.marcotancredo.bookstoremanager.model.users.mapper.UserMapper;
import br.com.marcotancredo.bookstoremanager.model.users.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final static UserMapper userMapper = UserMapper.INSTANCE;

    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public MessageDTO create(UserDTO userToCreateDTO){
        verifyIfExists(userToCreateDTO.getEmail(), userToCreateDTO.getUsername());
        User userToCreate = userMapper.toModel(userToCreateDTO);
        User createdUser = userRepository.save(userToCreate);

        return creationMessage(createdUser);

    }

    public void delete(Long id){
        verifyIfExists(id);
        userRepository.deleteById(id);
    }

    private MessageDTO creationMessage(User createdUser) {
        String createdUserName = createdUser.getUsername();
        Long createdUserId = createdUser.getId();

        String createdUserMessage = String.format("User %s with ID %s successfully created!", createdUserName, createdUserId);

        return MessageDTO.builder()
                .message(createdUserMessage)
                .build();
    }

    private void verifyIfExists(String email, String username) {
        Optional<User> foundUser = userRepository.findByEmailOrUsername(email, username);
        if(foundUser.isPresent()){
            throw new UserAlreadyExistsException(email, username);
        }
    }
    private void verifyIfExists(Long id) {
        userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
    }
}
