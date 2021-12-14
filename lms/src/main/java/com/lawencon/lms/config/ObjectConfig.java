package com.lawencon.lms.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import liquibase.integration.spring.SpringLiquibase;

@Configuration
@ComponentScan(basePackages = "com.lawencon")
public class ObjectConfig {
	
	@Bean("initTable")
	@Autowired
	public SpringLiquibase initTable(@Autowired DataSource dataSource) {
		SpringLiquibase springLiquibase = new SpringLiquibase();
		springLiquibase.setDataSource(dataSource);
		springLiquibase.setChangeLog("/db/migration/script/init_table.sql");
		return springLiquibase;
	}
	
	
	@Bean
	@DependsOn("initTable")
	@Autowired
	public SpringLiquibase initData(@Autowired DataSource dataSource) {
		SpringLiquibase springLiquibase = new SpringLiquibase();
		springLiquibase.setDataSource(dataSource);
		springLiquibase.setChangeLog("/db/migration/script/init_data.sql");
		return springLiquibase;
	}
}
