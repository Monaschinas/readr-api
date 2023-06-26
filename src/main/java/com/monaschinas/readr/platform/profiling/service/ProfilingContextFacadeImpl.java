package com.monaschinas.readr.platform.profiling.service;

import com.monaschinas.readr.platform.profiling.api.internal.ProfilingContextFacade;
import com.monaschinas.readr.platform.profiling.domain.model.Profile;
import com.monaschinas.readr.platform.profiling.domain.model.Role;
import com.monaschinas.readr.platform.profiling.domain.service.ProfileService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class ProfilingContextFacadeImpl implements ProfilingContextFacade {
  private final ProfileService profileService;

  public ProfilingContextFacadeImpl(ProfileService profileService) {
    this.profileService = profileService;
  }

  @Override
  public List<Role> getProfileRolesByUserId(Long userId) {
    return this.profileService.getAllByUserId(userId).stream()
      .map(Profile::getRole)
      .collect(Collectors.toList());
  }
}
