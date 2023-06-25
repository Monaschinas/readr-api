package com.monaschinas.readr.platform.security.domain.service;

public interface PasswordHashingService {
  String getHash(String password);
  boolean verifyPassword(String password, String hashedPassword);
}
