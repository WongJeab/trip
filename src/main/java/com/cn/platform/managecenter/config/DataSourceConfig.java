package com.cn.platform.managecenter.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

/**
 * Created by anphy on 2017/9/22.
 */
@Configuration
public class DataSourceConfig {

    @Bean(name = "defDataSource")
    @Primary
    @ConfigurationProperties(prefix="spring.datasource.def")
    public DataSource dataSource1() {
        return DataSourceBuilder.create().build();
    }

    /**
     * 为测试多数据源而配置，目前没用
     */
   @Bean(name = "testDataSource")
    @ConfigurationProperties(prefix="spring.datasource.test")
    public DataSource dataSource2() {
        return DataSourceBuilder.create().build();
    }

}
