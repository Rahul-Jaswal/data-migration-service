package com.catalystone.migrationservice.tenancy;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.hibernate.MultiTenancyStrategy;
import org.hibernate.cfg.Environment;
import org.hibernate.context.spi.CurrentTenantIdentifierResolver;
import org.hibernate.engine.jdbc.connections.spi.MultiTenantConnectionProvider;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

@org.springframework.context.annotation.Configuration
@EnableJpaRepositories(entityManagerFactoryRef = "firstEntityManager" ,basePackages ={
		"com.catalystone.migrationservice"
})
public class MultiTenancyFullConfiguration {
	@Bean("Connection1")
	@Order(Ordered.HIGHEST_PRECEDENCE)
	public MultiTenantConnectionProvider getMultiTenantConnectionProviderImpl() {
		return new MultiTenantConnectionProviderImpl();
	}

	@Bean
	public DataSource getDefaultDatasource() {
		return DataSourceBuilder.create().type(SimpleDriverDataSource.class).build();
	}
	
	
	 @Bean("v1")
	 public JpaVendorAdapter jpaVendorAdapter() {
	     return new HibernateJpaVendorAdapter();
	 }

	@Primary
	@Bean("firstEntityManager")
	public LocalContainerEntityManagerFactoryBean entityManagerFactory( DataSource dataSource, JpaProperties jpaProperties,
																	   @Qualifier("Connection1") MultiTenantConnectionProvider multiTenantConnectionProviderImpl,
																	   CurrentTenantIdentifierResolver currentTenantIdentifierResolverImpl) {


		Map<String, Object> properties = new HashMap<>();
		properties.putAll(jpaProperties.getProperties());

		properties.put(Environment.MULTI_TENANT, MultiTenancyStrategy.DATABASE);
		properties.put(Environment.MULTI_TENANT_CONNECTION_PROVIDER, multiTenantConnectionProviderImpl);
		properties.put(Environment.MULTI_TENANT_IDENTIFIER_RESOLVER, currentTenantIdentifierResolverImpl);
		LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
		em.setPackagesToScan("com.catalystone.migrationservice");
		em.setJpaVendorAdapter(jpaVendorAdapter());
		em.setJpaPropertyMap(properties);
		return em;
	}


}