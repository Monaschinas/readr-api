package com.monaschinas.readr.platform.security.resource;

import jakarta.persistence.Entity;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@With
public class AuthenticateResource {
  private Long id;
  private String username;
  private String email;
  public String firstName;
  public String lastName;
  public String urlPhoto;
  public boolean isAuthor;
  private String token;
}
