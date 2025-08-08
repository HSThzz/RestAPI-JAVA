package com.AuthorApi.AuthorApi.controllers;


import com.AuthorApi.AuthorApi.controllers.dto.AuthorDTO;
import com.AuthorApi.AuthorApi.models.AuthorModel;
import com.AuthorApi.AuthorApi.services.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@RequestMapping("/Author")
@RestController
public class AuthorController {


    @Autowired
    private AuthorService authorService;

    @GetMapping("{id}")
    public ResponseEntity<Object> getAuthor(@PathVariable UUID id) throws Exception {

        Optional<AuthorModel> author = authorService.getAuthor(id);

        return ResponseEntity.status(200).body(author);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Object> deleteAuthor(@PathVariable UUID id) throws Exception {

        ResponseEntity<Object> status = authorService.deleteAuthor(id);

        return switch (status.getStatusCode()) {
            case HttpStatus.NO_CONTENT -> ResponseEntity.status(204).body("No content");
            case HttpStatus.BAD_REQUEST -> ResponseEntity.status(400).body("Erro de Validação");
            default -> status;
        };
    }

    @PutMapping("{id}")
    public ResponseEntity<Object> putAuthor(@PathVariable UUID id, @RequestBody AuthorModel author) throws Exception {

        ResponseEntity<Object> status = authorService.putAuthor(id, author);

        return switch (status.getStatusCode()) {
            case HttpStatus.NO_CONTENT -> ResponseEntity.status(204).body("Erro de Validação");
            case HttpStatus.UNPROCESSABLE_ENTITY -> ResponseEntity.status(422).body("Erro de Validação");
            case HttpStatus.CONFLICT -> ResponseEntity.status(409).body("Registro duplicado");
            default -> status;
        };
    }

    @PostMapping()
    public ResponseEntity<Object> postAuthor(@RequestBody AuthorDTO author) throws Exception {

        ResponseEntity<Object> status = authorService.postAuthor(author.setAuthor());

        return switch (status.getStatusCode()) {
            case HttpStatus.UNPROCESSABLE_ENTITY -> ResponseEntity.status(422).body("Erro de Validação");
            case HttpStatus.CONFLICT -> ResponseEntity.status(409).body("Registro duplicado");
            default -> status;
        };

    }

}
