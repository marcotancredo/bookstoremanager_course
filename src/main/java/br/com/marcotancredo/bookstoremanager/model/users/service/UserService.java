package br.com.marcotancredo.bookstoremanager.model.users.service;

import br.com.marcotancredo.bookstoremanager.model.users.dto.MessageDTO;
import br.com.marcotancredo.bookstoremanager.model.users.dto.UserDTO;
import br.com.marcotancredo.bookstoremanager.model.users.entity.User;
import br.com.marcotancredo.bookstoremanager.model.users.exception.UserAlreadyExistsException;
import br.com.marcotancredo.bookstoremanager.model.users.exception.UserNotFoundException;
import br.com.marcotancredo.bookstoremanager.model.users.mapper.UserMapper;
import br.com.marcotancredo.bookstoremanager.model.users.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static br.com.marcotancredo.bookstoremanager.model.users.utils.MessageDTOUtils.creationMessage;
import static br.com.marcotancredo.bookstoremanager.model.users.utils.MessageDTOUtils.updatedMessage;

@Service
public class UserService {

    private final static UserMapper userMapper = UserMapper.INSTANCE;

    private UserRepository userRepository;

    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public MessageDTO create(UserDTO userToCreateDTO){
        verifyIfExists(userToCreateDTO.getEmail(), userToCreateDTO.getUsername());
        User userToCreate = userMapper.toModel(userToCreateDTO);
        userToCreate.setPassword(passwordEncoder.encode(userToCreate.getPassword()));

        User createdUser = userRepository.save(userToCreate);
        return creationMessage(createdUser);

    }

    public void delete(Long id){
        verifyAndGetIfExists(id);
        userRepository.deleteById(id);
    }

    public MessageDTO update(Long id, UserDTO userToUpdateDTO){
        User foundUser = verifyAndGetIfExists(id);
        userToUpdateDTO.setId(foundUser.getId());
        User userToUpdate = userMapper.toModel(userToUpdateDTO);
        userToUpdate.setPassword(passwordEncoder.encode(userToUpdate.getPassword()));
        userToUpdate.setCreatedDate(foundUser.getCreatedDate());

        User updatedUser = userRepository.save(userToUpdate);

        return updatedMessage(updatedUser);

    }

    private void verifyIfExists(String email, String username) {
        Optional<User> foundUser = userRepository.findByEmailOrUsername(email, username);
        if(foundUser.isPresent()){
            throw new UserAlreadyExistsException(email, username);
        }
    }
    private User verifyAndGetIfExists(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
    }
}
