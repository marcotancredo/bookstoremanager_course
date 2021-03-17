package br.com.marcotancredo.bookstoremanager.model.publishers.service;

import br.com.marcotancredo.bookstoremanager.model.publishers.dto.PublisherDTO;
import br.com.marcotancredo.bookstoremanager.model.publishers.entity.Publisher;
import br.com.marcotancredo.bookstoremanager.model.publishers.exception.PublisherAlreadyExistsException;
import br.com.marcotancredo.bookstoremanager.model.publishers.exception.PublisherNotFoundException;
import br.com.marcotancredo.bookstoremanager.model.publishers.mapper.PublisherMapper;
import br.com.marcotancredo.bookstoremanager.model.publishers.repository.PublisherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PublisherService {

    private final static PublisherMapper publisherMapper = PublisherMapper.INSTANCE;

    private PublisherRepository publisherRepository;

    @Autowired
    public PublisherService(PublisherRepository publisherRepository){
        this.publisherRepository = publisherRepository;
    }

    public PublisherDTO create(PublisherDTO publisherDTO){
        verifyIfExists(publisherDTO.getName(), publisherDTO.getCode());

        Publisher publisherToCreate = publisherMapper.toModel(publisherDTO);
        Publisher createdPublisher = publisherRepository.save(publisherToCreate);
        return publisherMapper.toDTO(createdPublisher);
    }

    private void verifyIfExists(String name, String code) {
        Optional<Publisher> duplicatedPublisher = publisherRepository
                .findByNameOrCode(name, code);

        if(duplicatedPublisher.isPresent()){
            throw new PublisherAlreadyExistsException(name, code);
        }
    }

    public PublisherDTO findById(Long id){
        return publisherRepository.findById(id)
                .map(publisherMapper::toDTO)
                .orElseThrow(() -> new PublisherNotFoundException(id));
    }

    public List<PublisherDTO> findAll(){
        return publisherRepository.findAll()
                .stream()
                .map(publisherMapper::toDTO)
                .collect(Collectors.toList());
    }
}
