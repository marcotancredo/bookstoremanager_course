package br.com.marcotancredo.bookstoremanager.books.builder;

import br.com.marcotancredo.bookstoremanager.model.books.dto.BookRequestDTO;
import br.com.marcotancredo.bookstoremanager.model.users.dto.UserDTO;
import br.com.marcotancredo.bookstoremanager.users.builder.UserDTOBuilder;
import lombok.Builder;

@Builder
public class BookRequestDTOBuilder {

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
    private final Long authorId = 3L;

    @Builder.Default
    private final Long publisherId = 2L;

    private final UserDTO userDTO = UserDTOBuilder.builder().build().builderUserDTO();

    public BookRequestDTO buildBookRequestDTO(){
        return new BookRequestDTO(
                id,
                name,
                isbn,
                pages,
                chapters,
                authorId,
                publisherId
        );
    }
}
