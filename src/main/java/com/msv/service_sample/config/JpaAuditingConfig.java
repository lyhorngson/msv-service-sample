package com.msv.service_sample.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.Optional;

/**
 * JPA auditing config.
 * Automatically sets createdBy / updatedBy on all BaseEntity instances.
 *
 * TODO (Next Sprint): Replace with Spring Security context principal
 * when authentication is added.
 */
@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorProvider")
public class JpaAuditingConfig {

    @Bean
    public AuditorAware<String> auditorProvider() {
        // TODO: Replace with SecurityContextHolder.getContext().getAuthentication().getName()
        return () -> Optional.of("SYSTEM");
    }
}