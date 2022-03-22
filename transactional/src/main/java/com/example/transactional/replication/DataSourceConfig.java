package com.example.transactional.replication;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.LazyConnectionDataSourceProxy;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class})
@EnableConfigurationProperties(SpringDataSourceProperties.class)
public class DataSourceConfig {

    private final SpringDataSourceProperties springDataSourceProperties;

    public DataSourceConfig(SpringDataSourceProperties springDataSourceProperties) {
        this.springDataSourceProperties = springDataSourceProperties;
    }


    @Bean
    public DataSource routingDataSource(SpringDataSourceProperties springDataSourceProperties) {
        Map<Object, Object> dataSources = createDataSources(springDataSourceProperties);
        ReplicationRoutingDataSource replicaRoutingDataSource = new ReplicationRoutingDataSource();
        replicaRoutingDataSource.setTargetDataSources(dataSources);
        replicaRoutingDataSource.setDefaultTargetDataSource(dataSources.get("master"));
        return replicaRoutingDataSource;
    }

    @Bean
    public DataSource dataSource(DataSource routingDataSource) {
        // 트랜잭션 실행시에 Connection 객체를 가져오기 위해 LazyConnectionDataSourceProxy로 설정
        return new LazyConnectionDataSourceProxy(routingDataSource);
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource) {
        LocalContainerEntityManagerFactoryBean entityManagerFactory = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactory.setDataSource(dataSource);
        entityManagerFactory.setPackagesToScan("com.example.transactional");
        entityManagerFactory.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        entityManagerFactory.setJpaPropertyMap(Map.of(
            "hibernate.show_sql", true,
            "hibernate.format_sql", true,
            "hibernate.use_sql_comments", false,
            "hibernate.dialect", "org.hibernate.dialect.MySQL5InnoDBDialect"
        ));
        return entityManagerFactory;
    }

    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }

    private Map<Object, Object> createDataSources(SpringDataSourceProperties springDataSourceProperties) {
        Map<Object, Object> dataSources = new LinkedHashMap<>();
        dataSources.put("master", createDataSource(springDataSourceProperties.getMaster()));
        dataSources.put("slave", createDataSource(springDataSourceProperties.getSlave()));

        return dataSources;
    }

    private DataSource createDataSource(SpringDataSourceProperties.DataSourceProperty dataSourceProperty) {
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setJdbcUrl(dataSourceProperty.getUrl());
        dataSource.setDriverClassName(dataSourceProperty.getDriverClassName());
        dataSource.setUsername(dataSourceProperty.getUsername());
        dataSource.setPassword(dataSourceProperty.getPassword());
        return dataSource;
    }
}
