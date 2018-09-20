package com.example.multidatasource.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

/**
 * Created by Douglee on 2018/9/20.
 */
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "entityManagerFactory",
        basePackages = "com.example.multidatasource.repo.mssql"
)
public class MSSQLDataSourceConfig {

    @Bean
    @ConfigurationProperties("spring.datasource.mssql")
    public DataSource msDataSource(){
        return DataSourceBuilder.create().build();
    }
}
