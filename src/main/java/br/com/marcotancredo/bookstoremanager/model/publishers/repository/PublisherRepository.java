package br.com.marcotancredo.bookstoremanager.model.publishers.repository;

import br.com.marcotancredo.bookstoremanager.model.publishers.entity.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PublisherRepository extends JpaRepository<Publisher, Long> {
}
