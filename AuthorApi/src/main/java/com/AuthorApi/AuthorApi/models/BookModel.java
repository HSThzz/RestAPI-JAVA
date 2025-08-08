package com.AuthorApi.AuthorApi.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity(name = "TB_BOOK")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false, unique = true)
    private String nome;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private GeneroLivro generoLivro;

    @Column(nullable = false)
    private LocalDate dataPublicacao;

    @Column(nullable = false)
    private BigDecimal preco;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "tb_book_author",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id")
    )
    private Set<AuthorModel> authors = new HashSet<>();
}
