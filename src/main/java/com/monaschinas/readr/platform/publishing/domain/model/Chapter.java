package com.monaschinas.readr.platform.publishing.domain.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@With
@Entity
@Table(name = "chapters")
public class Chapter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @NotBlank
    @Size(max = 24)
    private String title;

    @NotNull
    @NotBlank
    @Size(max = 64)
    private String document_content_url;

    @NotNull
    @NotBlank
    private Long bookId;
}
