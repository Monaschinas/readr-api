package com.monaschinas.readr.platform.security.domain.service.communication;

import com.monaschinas.readr.platform.security.resource.AuthenticateResource;
import com.monaschinas.readr.platform.shared.domain.service.communication.BaseResponse;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthenticateResponse extends BaseResponse<AuthenticateResource> {

  public AuthenticateResponse(String message) {
    super(message);
  }

  public AuthenticateResponse(AuthenticateResource resource) {
    super(resource);
  }
}
