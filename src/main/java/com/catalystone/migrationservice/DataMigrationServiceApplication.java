package com.catalystone.migrationservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

import com.catalystone.auth.spring.common.configuration.CoConfiguration;
import com.catalystone.tenancy.api.CoTenancyApiClientConfiguration;

@SpringBootApplication
@Import({CoTenancyApiClientConfiguration.class, CoConfiguration.class})
public class DataMigrationServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(DataMigrationServiceApplication.class, args);
	}

}
