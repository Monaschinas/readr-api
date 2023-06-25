package com.monaschinas.readr.platform.security.domain.service.communication;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterRequest {
  public String username;
  public String email;
  public String password;
}
