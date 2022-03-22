package com.example.transactional.replication;

import lombok.Data;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;


@Data
@Component
@ConfigurationProperties("spring.datasource")
public class SpringDataSourceProperties {
    private DataSourceProperty master;
    private DataSourceProperty slave;

    @Data
    public static class DataSourceProperty {
        private String url;
        private String username;
        private String password;
        private String driverClassName;
    }
}
