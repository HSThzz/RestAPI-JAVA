package com.AuthorApi.AuthorApi.services;


import com.AuthorApi.AuthorApi.controllers.dto.AuthorDTO;
import com.AuthorApi.AuthorApi.models.AuthorModel;
import com.AuthorApi.AuthorApi.repositories.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class AuthorService {

    @Autowired
    AuthorRepository authorRepository;
    private AuthorRepository authorRepository1;


    public AuthorDTO getAuthor(UUID id) throws Exception{

        AuthorModel authorModel = authorRepository.findById(id)
                .orElseThrow(() -> new Exception("Autor nao existente"));

        return new AuthorDTO(
                id,
                authorModel.getNome(),
                authorModel.getDataNascimento(),
                authorModel.getNacionalidade());
    }

    public ResponseEntity<Object> deleteAuthor(UUID id) throws Exception{
        if(authorRepository.findById(id).isEmpty()){
            throw new Exception("autor nao existe");
        }
        authorRepository.deleteById(id);

        return ResponseEntity.status(200).body("Autor deletado com sucesso");
    }

    public ResponseEntity<Object> putAuthor(UUID id, AuthorModel author) throws Exception{
        if(authorRepository.findById(id).isEmpty()){
            throw new Exception("Autor nao existente");
        }

        AuthorModel newAuthor = authorRepository.findById(id).get();
        if(newAuthor.getNome() == null || author.getNome().trim().isEmpty()){
            throw new Exception("Nome é obrigatorio");
        } else if (newAuthor.getNacionalidade() == null || author.getNacionalidade().trim().isEmpty()) {
            throw new Exception("Nacionalidade é obrigatoria");
        } else if (newAuthor.getDataNascimento() == null){
            throw new Exception("Data de nascimento é obrigatoria");
        }

        newAuthor.setNome(author.getNome());
        newAuthor.setNacionalidade(author.getNacionalidade());
        newAuthor.setDataNascimento(author.getDataNascimento());

        authorRepository.save(newAuthor);

        return ResponseEntity.status(200).body("Autor atualizado com sucesso");

    }

    public ResponseEntity<Object> postAuthor(AuthorModel author) throws Exception{

        if (authorRepository.findByNome(author.getNome()).isPresent()) {
            throw new Exception("Registro duplicado");
        }

        authorRepository.save(author);
        return ResponseEntity.status(201).body("Autor criado com sucesso");
    }
}
