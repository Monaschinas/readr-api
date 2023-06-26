package com.monaschinas.readr.platform.security.api.rest;

import com.monaschinas.readr.platform.security.domain.service.UserService;
import com.monaschinas.readr.platform.security.domain.service.communication.AuthenticateRequest;
import com.monaschinas.readr.platform.security.domain.service.communication.AuthenticateResponse;
import com.monaschinas.readr.platform.security.domain.service.communication.RegisterRequest;
import com.monaschinas.readr.platform.security.exception.AppException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/security/users", produces = "application/json")
public class UserController {
  private final UserService userService;

  public UserController(UserService userService) {
    this.userService = userService;
  }

  @PostMapping("/sign-in")
  public ResponseEntity<?> authenticate(@RequestBody AuthenticateRequest request) {
    try {
      AuthenticateResponse response = userService.authenticate(request);
      return ResponseEntity.ok(response);
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }
  }

  @PostMapping("/sign-up")
  public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
    try {
      userService.register(request);
      return ResponseEntity.ok().body("Registration successful");
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }
  }
}
