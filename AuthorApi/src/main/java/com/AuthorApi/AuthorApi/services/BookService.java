package com.AuthorApi.AuthorApi.services;

import com.AuthorApi.AuthorApi.controllers.dto.BookDTO;
import com.AuthorApi.AuthorApi.models.AuthorModel;
import com.AuthorApi.AuthorApi.models.BookModel;
import com.AuthorApi.AuthorApi.repositories.AuthorRepository;
import com.AuthorApi.AuthorApi.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    public BookDTO getBook(UUID id) throws Exception{

        BookModel bookModel = bookRepository.findById(id)
                .orElseThrow(()-> new Exception("Livro nao encontrado"));

        return new BookDTO(
                bookModel.getNome(),
                bookModel.getGeneroLivro(),
                bookModel.getDataPublicacao(),
                bookModel.getPreco()
                );
    }

    public void deleteBook(UUID id) throws Exception{
        if(bookRepository.findById(id).isEmpty()){
            throw new Exception("Livro nao existe");
        }
        bookRepository.deleteById(id);
    }

    public void postBook(BookModel book) throws Exception{
        if(bookRepository.findBookByNome(book.getNome()).isPresent()){
            throw new Exception("Livro ja cadastrado");
        }

        Set<AuthorModel> autoresGerenciados = new HashSet<>();

        for (AuthorModel authorJson : book.getAuthors()){
            AuthorModel autorBanco = authorRepository.findById(authorJson.getId())
                    .orElseThrow(()-> new Exception("Autor nao encontrado"));
            autoresGerenciados.add(autorBanco);
        }

        book.setAuthors(autoresGerenciados);

        bookRepository.save(book);
    }

    public void putBook(UUID id, BookModel book) throws Exception{
        if(bookRepository.findById(id).isEmpty()){
            throw new Exception("Livro nao existe");
        }
        BookModel newLivro = bookRepository.findById(id).get();

        newLivro.setNome(book.getNome());
        newLivro.setGeneroLivro(book.getGeneroLivro());
        newLivro.setPreco(book.getPreco());
        newLivro.setDataPublicacao(book.getDataPublicacao());
        newLivro.setAuthors(book.getAuthors());

        bookRepository.save(newLivro);

    }


}
