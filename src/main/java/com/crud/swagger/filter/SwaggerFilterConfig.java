package com.crud.swagger.filter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.crud.swagger.repositories.PersonRepo;

@Configuration
public class SwaggerFilterConfig {

    private final PersonRepo prepo;

    public SwaggerFilterConfig(PersonRepo prepo) {
        this.prepo = prepo;
    }

    @Bean
    public SwaggerAuthFilter swaggerAuthFilter() {
        return new SwaggerAuthFilter(prepo);
    }

}
