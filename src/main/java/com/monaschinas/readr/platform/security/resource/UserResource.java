package com.monaschinas.readr.platform.security.resource;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@With
public class UserResource {
  private Long id;
  private String email;
}
