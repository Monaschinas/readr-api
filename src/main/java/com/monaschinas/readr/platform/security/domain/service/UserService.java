package com.monaschinas.readr.platform.security.domain.service;

import com.monaschinas.readr.platform.security.domain.service.communication.AuthenticateRequest;
import com.monaschinas.readr.platform.security.domain.service.communication.AuthenticateResponse;
import com.monaschinas.readr.platform.security.domain.service.communication.RegisterRequest;

public interface UserService {
  AuthenticateResponse authenticate(AuthenticateRequest model);
  void register(RegisterRequest model);
}
