package com.monaschinas.readr.platform.security.domain.persistence;

import com.monaschinas.readr.platform.security.domain.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
  Optional<User> findByEmail(String email);
  boolean exitsByEmail(String email);
}
