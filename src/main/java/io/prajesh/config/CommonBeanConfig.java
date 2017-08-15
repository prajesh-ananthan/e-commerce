package io.prajesh.config;

import org.jasypt.util.password.StrongPasswordEncryptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Prajesh Ananthan
 *         Created on 30/7/2017.
 */
@Configuration
public class CommonBeanConfig {
  @Bean
  public StrongPasswordEncryptor strongEncryptor() {
    return new StrongPasswordEncryptor();
  }
}
