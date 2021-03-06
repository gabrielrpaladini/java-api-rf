package com.paladini.libraryapi.service;

import com.paladini.libraryapi.api.model.entity.Book;
import com.paladini.libraryapi.api.model.repository.BookRepository;
import com.paladini.libraryapi.api.service.BookService;
import com.paladini.libraryapi.api.service.impl.BookServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
public class BookServiceTest {

    BookService service;

    @MockBean
    BookRepository repository;

    @BeforeEach
    public void setUp() {
        this.service = new BookServiceImpl(repository);
    }

    @Test
    @DisplayName("Deve")
    public void saveBookTest() {

        Book book = Book.builder().isbn("123").author("fulano").title("As aventuras").build();
        Mockito.when(repository.save(book)).thenReturn(Book.builder()
                .id(1l).isbn("123").
                title("As aventuras").author("fulano")
                .title("As aventuras").build());

        Book savedBook = service.save(book);

        assertThat(savedBook.getId()).isNotNull();
        assertThat(savedBook.getIsbn()).isEqualTo("123");
        assertThat(savedBook.getAuthor()).isEqualTo("fulano");
        assertThat(savedBook.getTitle()).isEqualTo("As aventuras");

    }

}
