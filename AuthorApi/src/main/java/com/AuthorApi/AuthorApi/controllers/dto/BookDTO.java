package com.AuthorApi.AuthorApi.controllers.dto;

import com.AuthorApi.AuthorApi.models.BookModel;
import com.AuthorApi.AuthorApi.models.GeneroLivro;

import java.math.BigDecimal;
import java.time.LocalDate;

public record BookDTO(String nome,
                      GeneroLivro generoLivro,
                      LocalDate dataPublicacao,
                      BigDecimal preco) {

    public BookModel setBook(){

        BookModel newBook = new BookModel();

        newBook.setNome(this.nome);
        newBook.setGeneroLivro(generoLivro);
        newBook.setDataPublicacao(dataPublicacao);
        newBook.setPreco(preco);

        return newBook;
    }
}
