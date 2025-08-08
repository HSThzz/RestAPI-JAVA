package com.AuthorApi.AuthorApi.controllers.dto;

import com.AuthorApi.AuthorApi.models.AuthorModel;

import java.time.LocalDate;

public record AuthorDTO(
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
