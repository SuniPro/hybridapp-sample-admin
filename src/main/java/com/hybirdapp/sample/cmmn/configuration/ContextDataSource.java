package com.hybirdapp.sample.cmmn.configuration;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

//@Configuration
@Configuration
public class ContextDataSource {

    @Bean
    public DataSource dataSource() {
        BasicDataSource basicDataSource = new BasicDataSource();
        basicDataSource.setDriverClassName("${jdbc.oracle.driverClassName}");
        basicDataSource.setUrl("${jdbc.oracle.url}");
        basicDataSource.setUsername("${jdbc.oracle.username}");
        basicDataSource.setPassword("${jdbc.oracle.password}");
        basicDataSource.setValidationQuery("SELECT 1 FROM DUAL");
        basicDataSource.setTestOnBorrow(true);
        return basicDataSource;
    }
}
