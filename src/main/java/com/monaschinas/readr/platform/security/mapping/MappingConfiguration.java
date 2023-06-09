package com.monaschinas.readr.platform.security.mapping;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration("securityMappingConfiguration")
public class MappingConfiguration {
  @Bean
  public UserMapper userMapper() {
    return new UserMapper();
  }
}
