package com.monaschinas.readr.platform.profile.mapping;

import com.monaschinas.readr.platform.profile.domain.model.Role;
import com.monaschinas.readr.platform.profile.resource.CreateRoleResource;
import com.monaschinas.readr.platform.profile.resource.RoleResource;
import com.monaschinas.readr.platform.profile.resource.UpdateRoleResource;
import com.monaschinas.readr.platform.shared.mapping.EnhancedModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.List;

public class RoleMapper implements Serializable {
  @Autowired
  EnhancedModelMapper mapper;

  public RoleResource toResource(Role model) {
    return mapper.map(model, RoleResource.class);
  }

  public Page<RoleResource> modelListPage(List<Role> modelList, Pageable pageable) {
    return new PageImpl<>(mapper.mapList(modelList, RoleResource.class), pageable, modelList.size());
  }

  public Role toModel(CreateRoleResource resource) {
    return mapper.map(resource, Role.class);
  }

  public Role toModel(UpdateRoleResource resource) {
    return mapper.map(resource, Role.class);
  }
}
