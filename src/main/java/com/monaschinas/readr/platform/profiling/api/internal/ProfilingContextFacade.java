package com.monaschinas.readr.platform.profiling.api.internal;

import com.monaschinas.readr.platform.profiling.domain.model.Role;

import java.util.List;

public interface ProfilingContextFacade {
  List<Role> getProfileRolesByUserId(Long userId);
}
