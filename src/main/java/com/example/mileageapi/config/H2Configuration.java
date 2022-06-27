package com.example.mileageapi.config;

import com.zaxxer.hikari.HikariDataSource;
import org.h2.tools.Server;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.sql.SQLException;

@Profile("h2")
@Configuration
public class H2Configuration {

  @Bean
  @ConfigurationProperties("spring.datasource.hikari")
  public HikariDataSource dataSource() throws SQLException {
    Server.createTcpServer("-tcp", "-tcpAllowOthers", "-ifNotExists", "-tcpPort", 9095 + "")
        .start();
    return new HikariDataSource();
  }
}
