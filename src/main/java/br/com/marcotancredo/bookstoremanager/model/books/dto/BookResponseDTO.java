package br.com.marcotancredo.bookstoremanager.model.books.dto;

import br.com.marcotancredo.bookstoremanager.model.authors.dto.AuthorDTO;
import br.com.marcotancredo.bookstoremanager.model.publishers.dto.PublisherDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookResponseDTO {

    private Long id;

    private String name;

    private String isbn;

    private Integer pages;

    private Integer chapters;

    private PublisherDTO publisherId;

    private AuthorDTO author;
}
