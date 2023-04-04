package com.hybirdapp.sample.cmmn.configuration;

import com.hybirdapp.sample.cmmn.mapper.OracleMapper;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

//@Configuration
public class ContextMapper {


    @Bean
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        PathMatchingResourcePatternResolver pmrpr = new PathMatchingResourcePatternResolver();
        ContextDataSource contextDataSource = new ContextDataSource();
        sqlSessionFactoryBean.setDataSource(contextDataSource.dataSource());
        sqlSessionFactoryBean.setConfigLocation(pmrpr.getResource("classpath:/eGovframework/sqlmap/config/sql-mapper-config.xml"));
        sqlSessionFactoryBean.setMapperLocations(pmrpr.getResources("classpath:/eGovframework/sqlmap/mappers/oracle/**/*.xml"));
        return sqlSessionFactoryBean.getObject();
    }

//    @Bean
//    public SqlSessionFactory sqlSessionFactory() throws Exception {
//        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
//        sqlSessionFactoryBean.setDataSource(dataSource());
//        sqlSessionFactoryBean.setConfigLocation(new ClassPathResource("mybatis-config.xml"));
//        sqlSessionFactoryBean.setMapperLocations(applicationContext.getResources("classpath:/sql-map/*.xml"));
//        return sqlSessionFactoryBean.getObject();
//    }


    @Bean
    public SqlSessionTemplate sqlSessionTemplate() throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory());
    }

    @Bean
    public MapperScannerConfigurer mapperScannerConfigurer() {
        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
        mapperScannerConfigurer.setBasePackage("com.plnc.**.service.impl");
        mapperScannerConfigurer.setSqlSessionFactoryBeanName("sqlSession");
        mapperScannerConfigurer.setAnnotationClass(OracleMapper.class);
        return mapperScannerConfigurer;
    }
}
