package com.AuthorApi.AuthorApi.controllers;


import com.AuthorApi.AuthorApi.models.BookModel;
import com.AuthorApi.AuthorApi.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RequestMapping("/book")
@RestController
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping
    public ResponseEntity<Object> getBook(@PathVariable UUID id) throws Exception{
        Optional<BookModel> livro = bookService.getBook(id);
        return ResponseEntity.status(200).build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Object> deleteBook(@PathVariable UUID id) throws Exception{
        bookService.deleteBook(id);
        return ResponseEntity.status(200).body("Livro apagado com sucesso");
    }

    @PostMapping
    public ResponseEntity<Object> postBook(@RequestBody BookModel book) throws Exception{
        bookService.postBook(book);

        return ResponseEntity.status(201).body("Livro criado com sucesso");
    }

    @PutMapping("{id}")
    public ResponseEntity<Object> putBook(@PathVariable UUID id,@RequestBody BookModel book) throws Exception{
       bookService.putBook(id, book);

       return ResponseEntity.status(200).body("Livro atualizado com sucesso");
    }
}
