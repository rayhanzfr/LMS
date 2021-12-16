package com.lawencon.lms.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.http.HttpMethod;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import liquibase.integration.spring.SpringLiquibase;

@Configuration
@ComponentScan(basePackages = "com.lawencon")
public class ObjectConfig {
	
//	@Bean("initTable")
//	@Autowired
//	public SpringLiquibase initTable(@Autowired DataSource dataSource) {
//		SpringLiquibase springLiquibase = new SpringLiquibase();
//		springLiquibase.setDataSource(dataSource);
//		springLiquibase.setChangeLog("/db/migration/script/init_table.sql");
//		return springLiquibase;
//	}
//	
//	
//	@Bean
//	@DependsOn("initTable")
//	@Autowired
//	public SpringLiquibase initData(@Autowired DataSource dataSource) {
//		SpringLiquibase springLiquibase = new SpringLiquibase();
//		springLiquibase.setDataSource(dataSource);
//		springLiquibase.setChangeLog("/db/migration/script/init_data.sql");
//		return springLiquibase;
//	}

	@Bean
	public BCryptPasswordEncoder passEncoder(){
		return new BCryptPasswordEncoder();
	}
	
	@Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {

            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**").allowedOrigins("*").allowedMethods(HttpMethod.POST.name(),
                        HttpMethod.GET.name(), HttpMethod.PATCH.name(), HttpMethod.DELETE.name(),
                        HttpMethod.PUT.name());
            }
        };
    }
	
}
