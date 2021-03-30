package br.com.marcotancredo.bookstoremanager.model.users.service;

import br.com.marcotancredo.bookstoremanager.model.users.dto.AuthenticatedUser;
import br.com.marcotancredo.bookstoremanager.model.users.entity.User;
import br.com.marcotancredo.bookstoremanager.model.users.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("User not found with username %s", username)));

        return new AuthenticatedUser(
                user.getUsername(),
                user.getPassword(),
                user.getRole().getDescription()
        );
    }
}
