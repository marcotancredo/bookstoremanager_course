package br.com.marcotancredo.bookstoremanager.model.publishers.service;

import br.com.marcotancredo.bookstoremanager.model.publishers.mapper.PublisherMapper;
import br.com.marcotancredo.bookstoremanager.model.publishers.repository.PublisherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PublisherService {

    private final static PublisherMapper publisherMapper = PublisherMapper.INSTANCE;

    private PublisherRepository publisherRepository;

    @Autowired
    public PublisherService(PublisherRepository publisherRepository){
        this.publisherRepository = publisherRepository;
    }
}
