package com.monaschinas.readr.platform.security.domain.service.communication;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthenticateRequest {
  @NotNull
  @NotBlank
  private String email;

  @NotNull
  @NotBlank
  private String password;
}
