package com.catalystone.migrationservice.tenancy.contextSetClasses;

import com.catalystone.auth.TenantIdentity;
import com.catalystone.tenancy.api.TenantProperties;

/**
 * Represents the context for a tenant specific operation.
 */
public class TenantContext {
    private final TenantProperties properties;

    public TenantContext(TenantProperties properties) {
        this.properties = properties;
    }

    public TenantProperties getProperties() {
        return properties;
    }

    /**
     * Get the tenant identity. Shortcut for {@code getProperties().getIdentity()}
     *
     * @return Tenant identity
     */
    public TenantIdentity getIdentity() {
        return getProperties().getIdentity();
    }

    @Override
    public boolean equals(Object obj) {
        return (obj instanceof TenantContext) && ((TenantContext) obj).getProperties()
                .getIdentity()
                .equals(this.getProperties()
                        .getIdentity());
    }

    @Override
    public int hashCode() {
        return this.properties.getIdentity()
                .hashCode();
    }

    @Override
    public String toString() {
        return String.format("[TenantContext (dns=%s, identity=%s)]", properties.getMonolithDnsName(),
                properties.getIdentity());
    }
}
