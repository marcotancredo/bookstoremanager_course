package br.com.marcotancredo.bookstoremanager.publisher.service;

import br.com.marcotancredo.bookstoremanager.model.publishers.mapper.PublisherMapper;
import br.com.marcotancredo.bookstoremanager.model.publishers.repository.PublisherRepository;
import br.com.marcotancredo.bookstoremanager.model.publishers.service.PublisherService;
import br.com.marcotancredo.bookstoremanager.publisher.builder.PublisherDTOBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class PublisherServiceTest {

    private final static PublisherMapper publisherMapper = PublisherMapper.INSTANCE;

    @Mock
    private PublisherRepository publisherRepository;

    @InjectMocks
    private PublisherService publisherService;

    private PublisherDTOBuilder publisherDTOBuilder;

    @BeforeEach
    void setUp() {
        publisherDTOBuilder = PublisherDTOBuilder.builder().build();
    }
}
