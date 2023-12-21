package com.servosys.config;

import org.jasypt.encryption.StringEncryptor;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JasyptConfig {
	
	 @Value("${jasypt.encryptor.algorithm}")
	 private String encryptedAlgorithm; 
	 @Value("${jasypt.encryptor.password}")
	 private String encryptedSecretKey;  // Corrected the @Value annotation


    @Bean
    public StringEncryptor jasyptStringEncryptor() {
        StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
        encryptor.setAlgorithm(encryptedAlgorithm);
        encryptor.setPassword(encryptedSecretKey); // This should match your jasypt.encryptor.password property
        return encryptor;
    }
}

