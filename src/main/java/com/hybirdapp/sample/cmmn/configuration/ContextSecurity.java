package com.hybirdapp.sample.cmmn.configuration;

import com.hybirdapp.sample.cmmn.security.CmmnAuthenticationFailureHandler;
import com.hybirdapp.sample.cmmn.security.CmmnInvalidSessionStrategyFilter;
import com.hybirdapp.sample.cmmn.security.CmmnAuthenticationSuccessHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.session.SessionManagementFilter;

//@Configuration
//@EnableWebSecurity
public class ContextSecurity extends WebSecurityConfigurerAdapter {

    @Bean
    public SessionManagementFilter sessionManagementFilter() {
        HttpSessionSecurityContextRepository securityContextRepository = new HttpSessionSecurityContextRepository();
        CmmnInvalidSessionStrategyFilter cmmnInvalidSessionStrategyFilter = new CmmnInvalidSessionStrategyFilter();
//        securityContextRepository.
        return null;
    }

    @Bean
    public CmmnAuthenticationSuccessHandler accessSuccessHandler() {
        return accessSuccessHandler();
    }

    @Bean
    public CmmnAuthenticationFailureHandler accessFailureHandler() {
        return accessFailureHandler();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        super.configure(http);
    }


}
