package com.cn.platform.managecenter.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.annotation.Resource;
import javax.sql.DataSource;

/**
 * Created by dhj on 2018/8/2.
 */
@Configuration
@MapperScan(basePackages = "cn.com.doone.sc.tx.cloud.sasms.controlcenter.dao.test",sqlSessionFactoryRef = "testSqlSessionFactory")
public class TestMybaitsConfig {
    @Resource
    @Qualifier("testDataSource")
    private DataSource testDataSource;

    @Value("${test.MapperLocations}")
    private String mapperLocations;

    @Bean(name = "testSqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory1() throws Exception{
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(testDataSource);
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(mapperLocations));
        return  bean.getObject();
    }
    //配置事务管理器
    @Bean(name = "testTransactionManager")
    public DataSourceTransactionManager testTransactionManager(@Qualifier("testDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = "testSqlSessionTemplate")
    public SqlSessionTemplate testSqlSessionTemplate(@Qualifier("testSqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
    @Bean(name = "testJdbcTemplate")
    public JdbcTemplate primaryJdbcTemplate(
            @Qualifier("testDataSource") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }
	@Bean(name = "testNamedParameterJdbcOperations")
	public NamedParameterJdbcOperations primaryNamedParameterJdbcOperations(
			@Qualifier("testDataSource") DataSource dataSource) {
		return new NamedParameterJdbcTemplate(dataSource);
	}
}
