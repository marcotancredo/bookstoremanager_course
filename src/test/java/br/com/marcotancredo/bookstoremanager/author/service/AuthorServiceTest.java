package br.com.marcotancredo.bookstoremanager.author.service;

import br.com.marcotancredo.bookstoremanager.author.builder.AuthorDTOBuilder;
import br.com.marcotancredo.bookstoremanager.model.authors.dto.AuthorDTO;
import br.com.marcotancredo.bookstoremanager.model.authors.entity.Author;
import br.com.marcotancredo.bookstoremanager.model.authors.exception.AuthorAlreadyExistsException;
import br.com.marcotancredo.bookstoremanager.model.authors.exception.AuthorNotFoundException;
import br.com.marcotancredo.bookstoremanager.model.authors.mapper.AuthorMapper;
import br.com.marcotancredo.bookstoremanager.model.authors.repository.AuthorRepository;
import br.com.marcotancredo.bookstoremanager.model.authors.service.AuthorService;
import lombok.var;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

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

    @Test
    void whenListAuthorsIsCalledThenItShouldBeReturned(){
        //given
        AuthorDTO expectedFoundAuthorDTO = authorDTOBuilder.buildAuthorDTO();
        Author expectedFoundAuthor = authorMapper.toModel(expectedFoundAuthorDTO);

        when(authorRepository.findAll()).thenReturn(Collections.singletonList(expectedFoundAuthor));

        List<AuthorDTO> foundAuthorsDTO = authorService.findAll();

        assertThat(foundAuthorsDTO.size(), is(1));
        assertThat(foundAuthorsDTO.get(0), is(equalTo(expectedFoundAuthorDTO)));
    }

    @Test
    void whenListAuthorsIsCalledThenAnEmptyListShouldBeReturned(){
        when(authorRepository.findAll()).thenReturn(Collections.EMPTY_LIST);

        List<AuthorDTO> foundAuthorsDTO = authorService.findAll();

        assertThat(foundAuthorsDTO.size(), is(0));
    }

    @Test
    void whenValidAuthorIdIsGivenThenItShouldBeDeleted() {
        //given
        AuthorDTO expectedDeletedAuthorDTO = authorDTOBuilder.buildAuthorDTO();
        Author expectedDeletedAuthor = authorMapper.toModel(expectedDeletedAuthorDTO);

        Long expectedDeletedAuthorId = expectedDeletedAuthorDTO.getId();

        doNothing().when(authorRepository).deleteById(expectedDeletedAuthorId);
        when(authorRepository.findById(expectedDeletedAuthorId)).thenReturn(Optional.of(expectedDeletedAuthor));

        authorService.delete(expectedDeletedAuthorId);

        verify(authorRepository, times(1)).deleteById(expectedDeletedAuthorId);
        verify(authorRepository, times(1)).findById(expectedDeletedAuthorId);
    }

    @Test
    void whenInValidAuthorIdIsGivenThenAnExceptionShouldBeThrown() {
        var expectedInvalidAuthorId = 2L;

        when(authorRepository.findById(expectedInvalidAuthorId)).thenReturn(Optional.empty());

        assertThrows(AuthorNotFoundException.class, () -> authorService.delete((expectedInvalidAuthorId)));
    }
}
