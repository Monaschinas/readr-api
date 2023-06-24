package com.monaschinas.readr.platform.security.middleware;

import com.monaschinas.readr.platform.profiling.api.internal.ProfilingContextFacade;
import com.monaschinas.readr.platform.profiling.domain.model.Role;
import com.monaschinas.readr.platform.security.domain.model.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor
public class UserDetailsImpl implements UserDetails {
  private Long id;
  private String username;
  private String email;
  private String password;
  private Collection<? extends GrantedAuthority> authorities;
  private static ProfilingContextFacade profilingContextFacade;


  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }

  @Override
  public boolean equals(Object other) {
    if (this == other) return true;
    if (other == null || getClass() != other.getClass()) return false;
    UserDetailsImpl user = (UserDetailsImpl) other;
    return Objects.equals(id, user.id);
  }

  public static UserDetailsImpl build(User user) {
    List<GrantedAuthority> authorities = profilingContextFacade.getProfileRolesByUserId(user.getId()).stream()
      .map(Role::getName)
      .distinct()
      .map(SimpleGrantedAuthority::new)
      .collect(Collectors.toList());

    return new UserDetailsImpl(
      user.getId(),
      user.getEmail(),
      user.getEmail(),
      user.getPassword(),
      authorities
    );
  }
}
