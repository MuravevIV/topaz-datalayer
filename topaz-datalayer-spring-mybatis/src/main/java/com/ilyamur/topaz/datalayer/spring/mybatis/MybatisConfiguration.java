package com.ilyamur.topaz.datalayer.spring.mybatis;

import com.ilyamur.topaz.datalayer.core.DataSourceConfiguration;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private DataSource dataSource;

    @Bean
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        SqlSessionFactoryBean sqlSessionFactory = new SqlSessionFactoryBean();
        sqlSessionFactory.setConfigLocation(new ClassPathResource(MYBATIS_CONFIG_XML));
        sqlSessionFactory.setDataSource(dataSource);
        return sqlSessionFactory.getObject();
    }

    @Bean
    public SqlSessionTemplate sqlSessionTemplate() throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory());
    }
}
