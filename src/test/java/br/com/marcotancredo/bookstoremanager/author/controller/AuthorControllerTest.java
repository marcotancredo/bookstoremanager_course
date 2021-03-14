package br.com.marcotancredo.bookstoremanager.author.controller;

import br.com.marcotancredo.bookstoremanager.author.builder.AuthorDTOBuilder;
import br.com.marcotancredo.bookstoremanager.model.authors.controller.AuthorController;
import br.com.marcotancredo.bookstoremanager.model.authors.service.AuthorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

@ExtendWith(MockitoExtension.class)
public class AuthorControllerTest {

    @Mock
    private AuthorService authorService;

    @InjectMocks
    private AuthorController authorController;

    private MockMvc mockMvc;

    private AuthorDTOBuilder authorDTOBuilder;

    @BeforeEach
    void setUp() {
        authorDTOBuilder = AuthorDTOBuilder.builder().build();
        mockMvc = MockMvcBuilders.standaloneSetup(authorController)
        .setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
        .setViewResolvers((s, locale) -> new MappingJackson2JsonView())
        .build();
    }
}
