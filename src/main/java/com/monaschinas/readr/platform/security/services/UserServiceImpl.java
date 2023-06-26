package com.monaschinas.readr.platform.security.services;

import com.monaschinas.readr.platform.security.domain.model.entity.User;
import com.monaschinas.readr.platform.security.domain.persistence.UserRepository;
import com.monaschinas.readr.platform.security.domain.service.PasswordHashingService;
import com.monaschinas.readr.platform.security.domain.service.UserService;
import com.monaschinas.readr.platform.security.domain.service.communication.AuthenticateRequest;
import com.monaschinas.readr.platform.security.domain.service.communication.AuthenticateResponse;
import com.monaschinas.readr.platform.security.domain.service.communication.RegisterRequest;
import com.monaschinas.readr.platform.security.exception.AppException;
import com.monaschinas.readr.platform.security.mapping.UserMapper;
//import com.monaschinas.readr.platform.security.middleware.JwtHandler;
import com.monaschinas.readr.platform.security.resource.AuthenticateResource;
import com.monaschinas.readr.platform.shared.exception.ResourceValidationException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {
  private static final String ENTITY = "User";

  private final UserRepository userRepository;
  private final PasswordHashingService passwordHashingService;
  private final UserMapper mapper;
  private final Validator validator;

  public UserServiceImpl(UserRepository userRepository, PasswordHashingService passwordHashingService, UserMapper mapper, Validator validator) {
    this.userRepository = userRepository;
    this.passwordHashingService = passwordHashingService;
    this.mapper = mapper;
    this.validator = validator;
  }

  @Override
  public AuthenticateResponse authenticate(AuthenticateRequest model) {
    Optional<User> user = userRepository.findByUsername(model.getUsername());

    if (user.isEmpty() || !passwordHashingService.verifyPassword(model.getPassword(), user.get().getPassword())) {
      throw new AppException("Invalid username or password");
    }

    User existingUser = user.get();

    AuthenticateResource resource = new AuthenticateResource()
      .withId(existingUser.getId())
      .withUsername(existingUser.getUsername())
      .withEmail(existingUser.getEmail())
      .withToken("HereGoesTheToken:D")
      .withFirstName(existingUser.getFirstName())
      .withLastName(existingUser.getLastName())
      .withUrlPhoto(existingUser.getUrlPhoto())
      .withAuthor(existingUser.isAuthor());

    //Authentication authentication = new UsernamePasswordAuthenticationToken(existingUser, null, null);

    return new AuthenticateResponse(resource);
  }

  @Override
  public void register(RegisterRequest model) {
    if (userRepository.existsByEmail(model.getEmail())) {
      throw new AppException(String.format("Email '%s' is already taken", model.getEmail()));
    }

    if (userRepository.existsByUsername(model.getUsername())) {
      throw new AppException(String.format("Username '%s' is already taken", model.getUsername()));
    }

    User user = mapper.toModel(model);
    user.setProfileId(404L);
    user.setPassword(passwordHashingService.getHash(model.getPassword()));

    Set<ConstraintViolation<User>> violations = validator.validate(user);

    if (!violations.isEmpty()) {
      throw new ResourceValidationException(ENTITY, violations);
    }

    try {
      userRepository.save(user);
    } catch (Exception e) {
      throw new AppException(String.format("An error occurred while saving the user: %s", e.getMessage()));
    }
  }
}
