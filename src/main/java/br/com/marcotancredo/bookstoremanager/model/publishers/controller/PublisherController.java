package br.com.marcotancredo.bookstoremanager.model.publishers.controller;

import br.com.marcotancredo.bookstoremanager.model.publishers.dto.PublisherDTO;
import br.com.marcotancredo.bookstoremanager.model.publishers.service.PublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/publishers")
public class PublisherController implements PublisherControllerDocs{

    private PublisherService publisherService;

    @Autowired
    public PublisherController(PublisherService publisherService) {
        this.publisherService = publisherService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PublisherDTO create(@RequestBody @Valid PublisherDTO publisherDTO) {
        return publisherService.create(publisherDTO);
    }

    @GetMapping("/{id}")
    public PublisherDTO findById(@PathVariable Long id) {
        return publisherService.findById(id);
    }

    @GetMapping
    public List<PublisherDTO> findAll() {
        return publisherService.findAll();
    }
}
