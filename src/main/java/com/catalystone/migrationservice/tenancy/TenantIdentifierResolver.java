package com.catalystone.migrationservice.tenancy;
import org.hibernate.context.spi.CurrentTenantIdentifierResolver;
import org.springframework.stereotype.Component;

@Component
public class TenantIdentifierResolver implements CurrentTenantIdentifierResolver {
    @Override
    public String resolveCurrentTenantIdentifier() {
        return "";
    }
    @Override
    public boolean validateExistingCurrentSessions() {
        return false;
    }
}
