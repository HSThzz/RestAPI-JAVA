package com.AuthorApi.AuthorApi.models;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;


@Entity(name = "TB_AUTHOR")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)//para usar as anotaçoes das datas
public class AuthorModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private LocalDate dataNascimento;

    @Column(nullable = false)
    private String nacionalidade;

    @CreatedDate
    @Column
    private LocalDateTime dataCadastro;

    @LastModifiedDate
    @Column
    private LocalDateTime dataAtualizacao;

    @Column
    private UUID idUsuario;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ManyToMany(mappedBy = "authors", fetch = FetchType.EAGER)
    private Set<BookModel> books = new HashSet<>();
}
