package br.com.marcotancredo.bookstoremanager.publisher.service;

import br.com.marcotancredo.bookstoremanager.model.publishers.dto.PublisherDTO;
import br.com.marcotancredo.bookstoremanager.model.publishers.entity.Publisher;
import br.com.marcotancredo.bookstoremanager.model.publishers.exception.PublisherAlreadyExistsException;
import br.com.marcotancredo.bookstoremanager.model.publishers.exception.PublisherNotFoundException;
import br.com.marcotancredo.bookstoremanager.model.publishers.mapper.PublisherMapper;
import br.com.marcotancredo.bookstoremanager.model.publishers.repository.PublisherRepository;
import br.com.marcotancredo.bookstoremanager.model.publishers.service.PublisherService;
import br.com.marcotancredo.bookstoremanager.publisher.builder.PublisherDTOBuilder;
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
import static org.mockito.Mockito.when;

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

    @Test
    void whenNewPublisherIsInformedThenItShouldBeCreated() {
        PublisherDTO expectedPublisherToCreateDTO = publisherDTOBuilder.buildPublisherDTO();
        Publisher expectedPublisherCreated = publisherMapper.toModel(expectedPublisherToCreateDTO);

        when(publisherRepository.findByNameOrCode(expectedPublisherToCreateDTO.getName(), expectedPublisherToCreateDTO.getCode()))
                .thenReturn(Optional.empty());
        when(publisherRepository.save(expectedPublisherCreated)).thenReturn(expectedPublisherCreated);

        PublisherDTO createdPublisherDTO = publisherService.create(expectedPublisherToCreateDTO);

        assertThat(createdPublisherDTO, is(equalTo(expectedPublisherToCreateDTO)));
    }

    @Test
    void whenExistingPublisherIsInformedThenAnExceptionShouldBeThrown() {
        PublisherDTO expectedPublisherToCreateDTO = publisherDTOBuilder.buildPublisherDTO();
        Publisher expectedPublisherDuplicated = publisherMapper.toModel(expectedPublisherToCreateDTO);

        when(publisherRepository.findByNameOrCode(expectedPublisherToCreateDTO.getName(), expectedPublisherToCreateDTO.getCode()))
                .thenReturn(Optional.of(expectedPublisherDuplicated));

        assertThrows(PublisherAlreadyExistsException.class, () -> publisherService.create(expectedPublisherToCreateDTO));
    }

    @Test
    void whenValidIdIsGivenThenAPublisherShouldBeReturned(){
        PublisherDTO expectedPublisherFoundDTO = publisherDTOBuilder.buildPublisherDTO();
        Publisher expectedPublisherFound = publisherMapper.toModel(expectedPublisherFoundDTO);
        var expectedPublisherFoundDTOId = expectedPublisherFoundDTO.getId();

        when(publisherRepository.findById(expectedPublisherFoundDTOId)).thenReturn(Optional.of(expectedPublisherFound));

        PublisherDTO foundPublisherDTO = publisherService.findById(expectedPublisherFoundDTOId);

        assertThat(foundPublisherDTO, is(equalTo(foundPublisherDTO)));

    }

    @Test
    void whenInvalidIdIsGivenThenAnExceptionShouldBeThrown(){
        PublisherDTO expectedPublisherFoundDTO = publisherDTOBuilder.buildPublisherDTO();

        var expectedPublisherFoundDTOId = expectedPublisherFoundDTO.getId();

        when(publisherRepository.findById(expectedPublisherFoundDTOId)).thenReturn(Optional.empty());

        assertThrows(PublisherNotFoundException.class, () -> publisherService.findById(expectedPublisherFoundDTOId));

    }

    @Test
    void whenListPublisherIsCalledThenItShouldBeReturned() {
        PublisherDTO expectedPublisherFoundDTO = publisherDTOBuilder.buildPublisherDTO();
        Publisher expectedPublisherFound = publisherMapper.toModel(expectedPublisherFoundDTO);

        when(publisherRepository.findAll()).thenReturn(Collections.singletonList(expectedPublisherFound));

        List<PublisherDTO> foundPublishersDTO = publisherService.findAll();

        assertThat(foundPublishersDTO.size(), is(1));
        assertThat(foundPublishersDTO.get(0), is(equalTo(expectedPublisherFoundDTO)));
    }

    @Test
    void whenListPublisherIsCalledThenAnEmptyListShouldBeReturned() {
        when(publisherRepository.findAll()).thenReturn(Collections.emptyList());

        List<PublisherDTO> foundPublishersDTO = publisherService.findAll();

        assertThat(foundPublishersDTO.size(), is(0));
    }
}
