package br.com.marcotancredo.bookstoremanager.model.authors.repository;

import br.com.marcotancredo.bookstoremanager.model.authors.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Long> {
}
