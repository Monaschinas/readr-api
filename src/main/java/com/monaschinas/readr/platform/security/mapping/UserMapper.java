package com.monaschinas.readr.platform.security.mapping;

import com.monaschinas.readr.platform.security.domain.model.entity.User;
import com.monaschinas.readr.platform.security.resource.UserResource;
import com.monaschinas.readr.platform.shared.mapping.EnhancedModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.List;

public class UserMapper implements Serializable {
  @Autowired
  EnhancedModelMapper mapper;

  public UserResource toResource(User model) {
    return mapper.map(model, UserResource.class);
  }

  public Page<UserResource> modeListPage(List<User> modelList, Pageable pageable) {
    return new PageImpl<>(mapper.mapList(modelList, UserResource.class), pageable, modelList.size());
  }
}
