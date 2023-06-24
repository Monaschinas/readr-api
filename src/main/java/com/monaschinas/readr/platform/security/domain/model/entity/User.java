package com.monaschinas.readr.platform.security.domain.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@With
@Entity
@Table(name = "users")
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotBlank
  @NotNull
  @Size(max = 64)
  @Column(unique = true)
  private String email;

  @NotBlank
  @Size(max = 124)
  private String password;

  @NotNull
  @Column(unique = true)
  private Long profileId;
}
