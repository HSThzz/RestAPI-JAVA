package com.AuthorApi.AuthorApi.repositories;

import com.AuthorApi.AuthorApi.models.BookModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface BookRepository extends JpaRepository<BookModel, UUID> {

    Optional<BookModel> findBookByNome(String nome);
}
