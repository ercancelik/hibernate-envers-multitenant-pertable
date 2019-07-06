package com.ercancelik.hibernate.envers.multitenant.table.config;

import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

public class AuditorAwareImpl implements AuditorAware<String> {
    @Override
    public Optional<String> getCurrentAuditor() {
        //should be retrieved from spring security context.
        return Optional.of("ADMIN");
    }
}
