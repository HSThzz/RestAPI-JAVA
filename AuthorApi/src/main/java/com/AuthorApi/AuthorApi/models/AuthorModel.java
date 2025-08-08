package com.AuthorApi.AuthorApi.models;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;


@Entity(name = "TB_AUTHOR")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AuthorModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private Date dataNascimento;

    @Column(nullable = false)
    private String nacionalidade;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ManyToMany(mappedBy = "authors", fetch = FetchType.EAGER)
    private Set<BookModel> books = new HashSet<>();
}
