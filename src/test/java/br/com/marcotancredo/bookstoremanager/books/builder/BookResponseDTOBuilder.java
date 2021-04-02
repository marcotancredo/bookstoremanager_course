package br.com.marcotancredo.bookstoremanager.books.builder;

import br.com.marcotancredo.bookstoremanager.author.builder.AuthorDTOBuilder;
import br.com.marcotancredo.bookstoremanager.model.authors.dto.AuthorDTO;
import br.com.marcotancredo.bookstoremanager.model.books.dto.BookResponseDTO;
import br.com.marcotancredo.bookstoremanager.model.publishers.dto.PublisherDTO;
import br.com.marcotancredo.bookstoremanager.model.users.dto.UserDTO;
import br.com.marcotancredo.bookstoremanager.publisher.builder.PublisherDTOBuilder;
import br.com.marcotancredo.bookstoremanager.users.builder.UserDTOBuilder;
import lombok.Builder;

@Builder
public class BookResponseDTOBuilder {

    @Builder.Default
    private final Long id = 1L;

    @Builder.Default
    private final String name = "Spring Boot Pro";

    @Builder.Default
    private final String isbn = "978-3-16-148410-0";

    @Builder.Default
    private final Integer pages = 200;

    @Builder.Default
    private final Integer chapters = 10;

    @Builder.Default
    private final PublisherDTO publisherDTO = PublisherDTOBuilder.builder().build().buildPublisherDTO();

    @Builder.Default
    private final AuthorDTO authorDTO = AuthorDTOBuilder.builder().build().buildAuthorDTO();

    private final UserDTO userDTO = UserDTOBuilder.builder().build().builderUserDTO();

    public BookResponseDTO buildBookResponseDTO(){
        return new BookResponseDTO(
                id,
                name,
                isbn,
                pages,
                chapters,
                publisherDTO,
                authorDTO
        );
    }
}
