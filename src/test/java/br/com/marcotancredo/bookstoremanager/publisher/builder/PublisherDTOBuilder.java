package br.com.marcotancredo.bookstoremanager.publisher.builder;

import br.com.marcotancredo.bookstoremanager.model.publishers.dto.PublisherDTO;
import lombok.Builder;

import java.time.LocalDate;

@Builder
public class PublisherDTOBuilder {

    @Builder.Default
    private final Long id = 1L;

    @Builder.Default
    private final String name = "Tancredo Editora";

    @Builder.Default
    private final String code = "T4NCR3D0";

    @Builder.Default
    private final LocalDate foundationDate = LocalDate.of(2020, 6, 1);

    public PublisherDTO buildPublisherDTO(){
        return new PublisherDTO(id,
                name,
                code,
                foundationDate);
    }
}
