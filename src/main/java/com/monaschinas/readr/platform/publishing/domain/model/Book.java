package com.monaschinas.readr.platform.publishing.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@With
@Entity
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @NotBlank
    @Size(max = 64)
    private String title;

    @NotNull
    @NotBlank
    @Size(max = 128)
    private String synopsis;

    @NotNull
    @NotBlank
    private Date published_at;

    //Relationships
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "language_id", nullable = false)
    @JsonIgnore
    private Language language;

    @ManyToOne
    @JoinColumn(name = "saga_id", nullable = false)
    @JsonIgnore
    private Saga sagaId;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "book_id", nullable = false)
    @JsonIgnore
    private BookStatus bookStatusId;

    private Long authorId;
}