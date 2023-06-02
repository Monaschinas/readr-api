package com.monaschinas.readr.platform.publishing.mapping;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration("publishingMappingConfiguration")
public class MappingConfiguration {
    @Bean
    public GenreMapper genreMapper(){ return new GenreMapper(); }
}
