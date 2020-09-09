package com.catalystone.migrationservice.tenancy.config;

import com.catalystone.tenancy.api.TenantApiClient;
import com.catalystone.tenancy.api.TenantProperties;
import com.microsoft.sqlserver.jdbc.SQLServerDriver;
import org.hibernate.engine.jdbc.connections.spi.AbstractDataSourceBasedMultiTenantConnectionProviderImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

import javax.sql.DataSource;
import java.sql.Driver;
import java.util.Base64;

public class MultiTenantConnectionProviderImpl extends AbstractDataSourceBasedMultiTenantConnectionProviderImpl {

    private static final long serialVersionUID = 1L;

    @Autowired
    TenantApiClient apiClient;

    @Override
    protected DataSource selectAnyDataSource() {
        return DataSourceBuilder.create().build();    //return empty datasource
    }


    @Override
    protected DataSource selectDataSource(String tenantIdentifier) {

        Driver driver = new SQLServerDriver();

        TenantProperties tp = apiClient.getProperties(tenantIdentifier).block();

//		TenantProperties tp = TenantContext.getCurrentTenant().getTenantContext().getProperties();


        String url = tp.get("DB_URL_CV").toString().replace("encrypt=true;", "");
        String username = tp.get("DB_USER_CV").toString();
        String encodedPassword = tp.get("DB_PASSWORD_CV").toString();
        String password = new String(Base64.getDecoder().decode(encodedPassword));


        return new SimpleDriverDataSource(driver, url,
                username, password);

    }
}
