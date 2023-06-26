package com.monaschinas.readr.platform.security.services;

import com.monaschinas.readr.platform.security.domain.service.PasswordHashingService;
//import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

@Service
public class PasswordHashingServiceImpl implements PasswordHashingService {

  @Override
  public String getHash(String password) {
    //return BCrypt.hashpw(password, BCrypt.gensalt());
    return password;
  }

  @Override
  public boolean verifyPassword(String password, String hashedPassword) {
   // return BCrypt.checkpw(password, hashedPassword);
    return password.equals(hashedPassword);
  }
}
