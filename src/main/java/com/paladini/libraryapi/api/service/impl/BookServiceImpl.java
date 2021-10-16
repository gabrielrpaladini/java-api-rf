package com.paladini.libraryapi.api.service.impl;

import com.paladini.libraryapi.api.model.entity.Book;
import com.paladini.libraryapi.api.model.repository.BookRepository;
import com.paladini.libraryapi.api.service.BookService;
import org.springframework.stereotype.Service;

@Service
public class BookServiceImpl implements BookService {

    private BookRepository repository;

    public BookServiceImpl(BookRepository repository) {
        this.repository = repository;
    }

    @Override
    public Book save(Book book) {
        return repository.save(book);
    }
}
