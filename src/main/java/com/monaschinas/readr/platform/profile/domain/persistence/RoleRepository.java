package com.monaschinas.readr.platform.profile.domain.persistence;

import com.monaschinas.readr.platform.profile.domain.model.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
  List<Role> findByRoleId(Long roleId);
  Page<Role> findByRoleId(Long roleId, Pageable pageable);
  Optional<Role> findByName(String name);
}
