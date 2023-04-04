package com.hybirdapp.sample.cmmn.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class ContextTransaction {

    @Bean
    public PlatformTransactionManager txManager() {
        ContextDataSource contextDataSource = new ContextDataSource();
        return new DataSourceTransactionManager(contextDataSource.dataSource());
    }
}
