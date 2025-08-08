package com.AuthorApi.AuthorApi.repositories;

import com.AuthorApi.AuthorApi.models.AuthorModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface AuthorRepository extends JpaRepository<AuthorModel, UUID> {


    Optional<AuthorModel> findByNome(String nome);
}
