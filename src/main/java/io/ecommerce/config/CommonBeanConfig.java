package io.ecommerce.config;

import org.jasypt.util.password.StrongPasswordEncryptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Locale;
import java.util.ResourceBundle;

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

  @Bean
  public ResourceBundle resourceBundle() {
    return ResourceBundle.getBundle("messages", Locale.ENGLISH);
  }
}
