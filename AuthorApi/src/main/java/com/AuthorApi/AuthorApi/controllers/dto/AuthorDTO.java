package com.AuthorApi.AuthorApi.controllers.dto;

import com.AuthorApi.AuthorApi.models.AuthorModel;

import java.time.LocalDate;
import java.util.UUID;

public record AuthorDTO(
        UUID id,
        String nome,
        LocalDate dataNascimento,
        String nacionalidade) {

    public AuthorModel setAuthor(){
        AuthorModel author = new AuthorModel();

        author.setNome(this.nome);
        author.setDataNascimento(this.dataNascimento);
        author.setNacionalidade(this.nacionalidade);

        return author;
    }
}
