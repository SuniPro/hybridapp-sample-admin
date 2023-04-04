package com.hybirdapp.sample.cmmn.configuration;

import egovframework.rte.fdl.idgnr.impl.EgovTableIdGnrServiceImpl;
import egovframework.rte.fdl.idgnr.impl.strategy.EgovIdGnrStrategyImpl;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;

public class ContextIdgen {

    @Bean(destroyMethod = "destroy")
    public EgovTableIdGnrServiceImpl egovTableIdGnrServiceImpl(DataSource dataSource, EgovIdGnrStrategyImpl egovIdGnrStrategyImpl) {
        EgovTableIdGnrServiceImpl egovTableIdGnrServiceImpl = new EgovTableIdGnrServiceImpl();
        egovTableIdGnrServiceImpl.setDataSource(dataSource);
        egovTableIdGnrServiceImpl.setStrategy(egovIdGnrStrategyImpl);
        egovTableIdGnrServiceImpl.setBlockSize(10);
        egovTableIdGnrServiceImpl.setTable("IDS");
        egovTableIdGnrServiceImpl.setTableName("SAMPLE");
        return egovTableIdGnrServiceImpl;
    }

    @Bean
    public EgovIdGnrStrategyImpl mixPrefixSample() {
        EgovIdGnrStrategyImpl egovIdGnrStrategy = new EgovIdGnrStrategyImpl();
        egovIdGnrStrategy.setPrefix("SAMPLE-");
        egovIdGnrStrategy.setCipers(5);
        egovIdGnrStrategy.setFillChar('0');
        return egovIdGnrStrategy;
    }
}
