package br.com.marcotancredo.bookstoremanager.model.authors.service;

import br.com.marcotancredo.bookstoremanager.model.authors.dto.AuthorDTO;
import br.com.marcotancredo.bookstoremanager.model.authors.entity.Author;
import br.com.marcotancredo.bookstoremanager.model.authors.exception.AuthorAlreadyExistsException;
import br.com.marcotancredo.bookstoremanager.model.authors.mapper.AuthorMapper;
import br.com.marcotancredo.bookstoremanager.model.authors.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorService {

    private final static AuthorMapper authorMapper = AuthorMapper.INSTANCE;

    private AuthorRepository authorRepository;

    @Autowired
    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public AuthorDTO create(AuthorDTO authorDTO){
        verifyIfExists(authorDTO.getName());
        Author authorToCreate = authorMapper.toModel(authorDTO);
        Author createdAuthor = authorRepository.save(authorToCreate);
        return authorMapper.toDTO(createdAuthor);
    }

    private void verifyIfExists(String authorName) {
        authorRepository.findByName(authorName)
                .ifPresent(author -> {throw new AuthorAlreadyExistsException(authorName);});
    }
}
