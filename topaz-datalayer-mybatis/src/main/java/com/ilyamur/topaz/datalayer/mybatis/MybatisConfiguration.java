package com.ilyamur.topaz.datalayer.mybatis;

import com.ilyamur.topaz.datalayer.core.DataSourceConfiguration;
import com.ilyamur.topaz.datalayer.mybatis.util.MandatoryTransactionSpringManagedTransactionFactory;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.io.ClassPathResource;

import javax.sql.DataSource;

@Configuration
@Import({
        DataSourceConfiguration.class
})
public class MybatisConfiguration {

    public static final String MYBATIS_CONFIG_XML = "mybatis/config.xml";

    @Bean
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
        SqlSessionFactoryBean sqlSessionFactory = new SqlSessionFactoryBean();
        sqlSessionFactory.setConfigLocation(new ClassPathResource(MYBATIS_CONFIG_XML));
        sqlSessionFactory.setDataSource(dataSource);
        sqlSessionFactory.setTransactionFactory(new MandatoryTransactionSpringManagedTransactionFactory());
        return sqlSessionFactory.getObject();
    }

    @Bean
    public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}