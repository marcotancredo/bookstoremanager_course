package br.com.marcotancredo.bookstoremanager.model.users.repository;

import br.com.marcotancredo.bookstoremanager.model.users.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmailOrUsername(String email, String username);

    Optional<User> findByUsername(String username);
}
