package com.tnicacio.algamoney.config;

import com.tnicacio.algamoney.components.SpringSecurityAuditorAware;
import com.tnicacio.algamoney.entities.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaAuditing
public class AuditConfig {

    @Bean public AuditorAware<String> auditorProvider(){
        return new SpringSecurityAuditorAware();
    }
}
