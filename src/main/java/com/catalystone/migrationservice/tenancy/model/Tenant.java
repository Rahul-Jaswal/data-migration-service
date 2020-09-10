package com.catalystone.migrationservice.tenancy.model;

import com.catalystone.migrationservice.tenancy.contextSetClasses.TenantContext;

public class Tenant {

    private static ThreadLocal<TenantContext> currentTenant = new ThreadLocal<>();

    private Tenant() {
    }

    public static TenantContext getCurrentTenant() {
        return currentTenant.get();
    }

    public static void setCurrentTenant(TenantContext tenant) {
        currentTenant.set(tenant);
    }

    public static void clear() {
        currentTenant.set(null);
    }
}