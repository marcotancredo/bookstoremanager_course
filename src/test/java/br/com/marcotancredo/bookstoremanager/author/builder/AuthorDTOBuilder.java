package br.com.marcotancredo.bookstoremanager.author.builder;

import br.com.marcotancredo.bookstoremanager.model.authors.dto.AuthorDTO;
import lombok.Builder;

@Builder
public class AuthorDTOBuilder {

    @Builder.Default
    private Long id = 1L;

    @Builder.Default
    private String name = "Marco Antonio Tancreod";

    @Builder.Default
    private int age = 33;

    public AuthorDTO buildAuthorDTO(){
        return new AuthorDTO(id, name, age);
    }
}
