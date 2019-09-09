package com.boot_jpa.www;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Controller
public class Controller {
    @Autowired
    private BookRepository repository;

    @GetMapping("/books")
    @ResponseBody
    public Iterable<Book> getList() {
        return repository.findAll();
    }

    @GetMapping("/books/{name}")
    @ResponseBody
    public Optional<List<Book>> getList(@PathVariable String name) {
        return Optional.ofNullable(repository.findByName(name));
    }

    @PostMapping("/books")
    public void createBook(@RequestBody Book vo) {

    }


}
