package br.com.marcotancredo.bookstoremanager.model.books.repository;

import br.com.marcotancredo.bookstoremanager.model.books.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
