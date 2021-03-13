package br.com.marcotancredo.bookstoremanager.model.users.repository;

import br.com.marcotancredo.bookstoremanager.model.users.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
