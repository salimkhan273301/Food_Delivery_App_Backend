package com.servosys.config;
import javax.sql.DataSource;

import org.jasypt.encryption.StringEncryptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;



@Configuration
/*
 * @PropertySources({
 * 
 * @PropertySource("classpath:application.properties"),
 * 
 * @PropertySource("classpath:other.properties"),
 * 
 * @PropertySource("classpath:another.properties") })
 */
@PropertySource("classpath:application.properties")
public class DataSourceConfig {

 @Value("${spring.datasource.url}")
 private String dbUrl;

 @Value("${spring.datasource.username}")
 private String dbUsername;

 @Value("${spring.datasource.password}")
 private String encryptedDbPassword;  // Corrected the @Value annotation

 @Bean
 
 public DataSource dataSource(StringEncryptor encryptor) {
     DriverManagerDataSource dataSource = new DriverManagerDataSource();
     dataSource.setDriverClassName("org.hibernate.dialect.MySQLDialect");
     dataSource.setUrl(dbUrl);
     dataSource.setUsername(dbUsername);
     
     try {
         String decryptedPassword = encryptor.decrypt(encryptedDbPassword);
         dataSource.setPassword(decryptedPassword); // Set the decrypted password
     } catch (Exception e) {
         // Handle decryption errors appropriately
         System.err.println("Error decrypting password: " + e.getMessage());
         throw new RuntimeException("Error decrypting password", e);
     }

     return dataSource;
 }
}
