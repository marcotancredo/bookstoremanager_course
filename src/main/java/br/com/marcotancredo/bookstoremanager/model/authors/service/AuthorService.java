package br.com.marcotancredo.bookstoremanager.model.authors.service;

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

}
