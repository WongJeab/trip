package com.cn.platform.managecenter.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.annotation.Resource;
import javax.sql.DataSource;

@Configuration
@MapperScan(basePackages = "com.cn.platform.managecenter.dao.def",sqlSessionFactoryRef = "defSqlSessionFactory")
public class DefMyBaitsConfig {
	
	@Resource
	@Qualifier("defDataSource")
	private DataSource defDataSource;
	@Value("${def.MapperLocations}")
	private String mapperLocations;
    @Primary
	@Bean(name = "defSqlSessionFactory")
	public SqlSessionFactory sqlSessionFactory1() throws Exception{
		SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
		bean.setDataSource(defDataSource);
		bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(mapperLocations));
		return  bean.getObject();
	}
	//配置事务管理器
	@Primary
    @Bean(name = "defTransactionManager")
    public DataSourceTransactionManager testTransactionManager(@Qualifier("defDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }
	@Primary
    @Bean(name = "defSqlSessionTemplate")
    public SqlSessionTemplate testSqlSessionTemplate(@Qualifier("defSqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
	@Bean(name = "defJdbcTemplate")
	@Primary
	public JdbcTemplate primaryJdbcTemplate(
			@Qualifier("defDataSource") DataSource dataSource) {
		return new JdbcTemplate(dataSource);
	}
	@Primary
	@Bean(name = "defNamedParameterJdbcOperations")
	public NamedParameterJdbcOperations primaryNamedParameterJdbcOperations(
			@Qualifier("defDataSource") DataSource dataSource) {
		return new NamedParameterJdbcTemplate(dataSource);
	}
	
	
}
