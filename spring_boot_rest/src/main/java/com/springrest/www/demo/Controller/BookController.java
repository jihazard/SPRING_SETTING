package com.springrest.www.demo.Controller;

import com.springrest.www.demo.model.Book;
import com.springrest.www.demo.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Min;
import java.util.List;

public class BookController {

    @Autowired
    private BookRepository bookRepository;

    @GetMapping("/books")
    public List<Book> findall() {
        return bookRepository.findAll();
    }


    @PostMapping("/books")
    @ResponseStatus(HttpStatus.CREATED)
    public Book creatBook(@RequestBody Book vo){
        return bookRepository.save(vo);
    }

    // Find
    @GetMapping("/books/{id}")
    Book findOne(@PathVariable @Min(1) Long id) throws Exception {
        return bookRepository.findById(id)
                .orElseThrow(() -> new Exception());
    }

    // Save or update
    @PutMapping("/books/{id}")
    Book saveOrUpdate(@RequestBody Book newBook, @PathVariable Long id) {

        return bookRepository.findById(id)
                .map(x -> {
                    x.setName(newBook.getName());
                    x.setAuthor(newBook.getAuthor());
                    x.setPrice(newBook.getPrice());
                    return bookRepository.save(x);
                })
                .orElseGet(() -> {
                    newBook.setId(id);
                    return bookRepository.save(newBook);
                });
    }

    @DeleteMapping("/books/{id}")
    void deleteBook(@PathVariable Long id) {
        bookRepository.deleteById(id);
    }
//
//    // Find
//    @GetMapping("/books/{id}")
//    Book findOne(@PathVariable Long id) {
//        return bookRepository.findById(id).orElse(new Book());
//    }
}
