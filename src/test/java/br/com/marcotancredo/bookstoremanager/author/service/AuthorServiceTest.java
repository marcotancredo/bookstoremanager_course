package br.com.marcotancredo.bookstoremanager.author.service;

import br.com.marcotancredo.bookstoremanager.author.builder.AuthorDTOBuilder;
import br.com.marcotancredo.bookstoremanager.model.authors.dto.AuthorDTO;
import br.com.marcotancredo.bookstoremanager.model.authors.entity.Author;
import br.com.marcotancredo.bookstoremanager.model.authors.exception.AuthorAlreadyExistsException;
import br.com.marcotancredo.bookstoremanager.model.authors.exception.AuthorNotFoundException;
import br.com.marcotancredo.bookstoremanager.model.authors.mapper.AuthorMapper;
import br.com.marcotancredo.bookstoremanager.model.authors.repository.AuthorRepository;
import br.com.marcotancredo.bookstoremanager.model.authors.service.AuthorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AuthorServiceTest {

    private final AuthorMapper authorMapper = AuthorMapper.INSTANCE;

    @Mock
    private AuthorRepository authorRepository;

    @InjectMocks
    private AuthorService authorService;

    private AuthorDTOBuilder authorDTOBuilder;

    @BeforeEach
    void setUp(){
        authorDTOBuilder = AuthorDTOBuilder.builder().build();
        AuthorDTO authorDTO = authorDTOBuilder.buildAuthorDTO();
    }

    @Test
    void whenNewAuthorIsInformedThenItShouldBeCreated(){
        //given
        AuthorDTO expectedAuthorToCreateDTO = authorDTOBuilder.buildAuthorDTO();
        Author excpectedCreatedAuthor = authorMapper.toModel(expectedAuthorToCreateDTO);

        //when
        when(authorRepository.save(excpectedCreatedAuthor)).thenReturn(excpectedCreatedAuthor);
        when(authorRepository.findByName(expectedAuthorToCreateDTO.getName())).thenReturn(Optional.empty());

        //then
        AuthorDTO createdAuthorDTO = authorService.create(expectedAuthorToCreateDTO);

        assertThat(createdAuthorDTO, is(equalTo(expectedAuthorToCreateDTO)));
    }

    @Test
    void whenExistingAuthorIsInformedThenAnExceptionSouldBeThrown(){
        //given
        AuthorDTO expectedAuthorToCreateDTO = authorDTOBuilder.buildAuthorDTO();
        Author expectedCreatedAuthor = authorMapper.toModel(expectedAuthorToCreateDTO);

        when(authorRepository.findByName(expectedAuthorToCreateDTO.getName()))
                .thenReturn(Optional.of(expectedCreatedAuthor));

        assertThrows(AuthorAlreadyExistsException.class, () -> authorService.create(expectedAuthorToCreateDTO));
    }

    @Test
    void whenValidIdIsGivenThenAnAuthorShouldBeReturned(){
        //given
        AuthorDTO expectedFoundAuthorDTO = authorDTOBuilder.buildAuthorDTO();
        Author expectedFoundAuthor = authorMapper.toModel(expectedFoundAuthorDTO);

        when(authorRepository.findById(expectedFoundAuthorDTO.getId()))
                .thenReturn(Optional.of(expectedFoundAuthor));

        AuthorDTO foundAuthorDTO = authorService.findById(expectedFoundAuthorDTO.getId());

        assertThat(foundAuthorDTO, is(equalTo(expectedFoundAuthorDTO)));
    }

    @Test
    void whenInvalidIdIsGivenThenAnExceptionShouldBeReturned(){
        //given
        AuthorDTO expectedFoundAuthorDTO = authorDTOBuilder.buildAuthorDTO();
        Author expectedFoundAuthor = authorMapper.toModel(expectedFoundAuthorDTO);

        when(authorRepository.findById(expectedFoundAuthorDTO.getId())).thenReturn(Optional.empty());

        assertThrows(AuthorNotFoundException.class, () -> authorService.findById(expectedFoundAuthorDTO.getId()));
    }
}
