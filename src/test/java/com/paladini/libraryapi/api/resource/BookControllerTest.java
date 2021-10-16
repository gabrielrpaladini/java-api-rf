package com.paladini.libraryapi.api.resource;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.paladini.libraryapi.api.dto.BookDTO;
import com.paladini.libraryapi.api.model.entity.Book;
import com.paladini.libraryapi.api.service.BookService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@WebMvcTest
@AutoConfigureMockMvc
public class BookControllerTest {

    static String BOOK_API = "/api/books";

    @Autowired
    MockMvc mvc;

    @MockBean
    BookService service;

    @Test
    @DisplayName("Should create book with success.")
    public void createBookTest() throws Exception {

        BookDTO dto = BookDTO.builder().author("Author").title("As aventuras").isbn("001").build();
        Book saved = Book.builder().id(101L).author("Author").title("As aventuras").isbn("001").build();
        BDDMockito.given(service.save(Mockito.any(Book.class)))
                .willReturn(saved);

        String json = new ObjectMapper().writeValueAsString(dto);

         MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .post(BOOK_API)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(json);

         mvc
                 .perform(request)
                 .andExpect(status().isCreated())
                 .andExpect(MockMvcResultMatchers.jsonPath("title").value(dto.getTitle()))
                 .andExpect(MockMvcResultMatchers.jsonPath("id").isNotEmpty());
    }

    @Test
    @DisplayName("Should raise an validating error when doesn't have book configuration")
    public void createInvalidBookTest() {

    }

}
