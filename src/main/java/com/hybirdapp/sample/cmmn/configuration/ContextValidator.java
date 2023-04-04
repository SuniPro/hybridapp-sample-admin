package com.hybirdapp.sample.cmmn.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;
import org.springmodules.validation.commons.DefaultBeanValidator;
import org.springmodules.validation.commons.DefaultValidatorFactory;
import org.springmodules.validation.commons.ValidatorFactory;

public class ContextValidator {


    @Autowired
    ApplicationContext ac;

    @Bean
    public DefaultBeanValidator beanValidator(ValidatorFactory validatorFactory) {
        DefaultBeanValidator defaultBeanValidator = new DefaultBeanValidator();
        defaultBeanValidator.setValidatorFactory(validatorFactory);
        return defaultBeanValidator;
    }

    @Bean
    public DefaultValidatorFactory defaultValidatorFactory() {
        DefaultValidatorFactory defaultValidatorFactory = new DefaultValidatorFactory();

        defaultValidatorFactory.setValidationConfigLocations(new Resource[]{
                ac.getResource("/WEB-INF/config/eGovframework/validator/validator-rules.xml"),
                ac.getResource("/WEB-INF/config/eGovframework/validator/validator.xml")
        });
        return defaultValidatorFactory;
    }
}
