package io.ecommerce.config;

import io.ecommerce.service.UserService;
import org.jasypt.springsecurity3.authentication.encoding.PasswordEncoder;
import org.jasypt.util.password.StrongPasswordEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * @author Prajesh Ananthan
 *         Created on 28/8/2017.
 */
@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

  private AuthenticationProvider authenticationProvider;

  @Autowired
  @Qualifier("daoAuthenticationProvider")
  public void setAuthenticationProvider(AuthenticationProvider authenticationProvider) {
    this.authenticationProvider = authenticationProvider;
  }

  @Bean
  public PasswordEncoder passwordEncoder(StrongPasswordEncryptor strongPasswordEncryptor) {
    PasswordEncoder passwordEncoder = new PasswordEncoder();
    passwordEncoder.setPasswordEncryptor(strongPasswordEncryptor);
    return passwordEncoder;
  }

  @Bean
  public DaoAuthenticationProvider daoAuthenticationProvider(PasswordEncoder passwordEncoder,
                                                             UserDetailsService userDetailsService) {

    DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
    daoAuthenticationProvider.setPasswordEncoder(passwordEncoder);
    daoAuthenticationProvider.setUserDetailsService(userDetailsService);
    return daoAuthenticationProvider;
  }

  @Autowired
  public void configureAuthManager(AuthenticationManagerBuilder authenticationManagerBuilder) {
    authenticationManagerBuilder.authenticationProvider(authenticationProvider);
  }

  // TODO: Login -> Index page
  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.csrf().ignoringAntMatchers("/console").disable()
        .headers().frameOptions().sameOrigin()
        .and().authorizeRequests().antMatchers("/**/favicon.ico").permitAll()
        .and().authorizeRequests().antMatchers("/product/**").permitAll()
        .and().authorizeRequests().antMatchers("/webjars/**").permitAll()
        .and().authorizeRequests().antMatchers("/static/css").permitAll()
        .and().authorizeRequests().antMatchers("/js").permitAll()
        .and().formLogin().loginPage("/login").permitAll()
        .and().authorizeRequests().antMatchers("/customer/**").authenticated()
        .and().authorizeRequests().antMatchers("/user/**").hasAuthority(UserService.ADMIN)
        .and().exceptionHandling().accessDeniedPage("/access_denied");
  }
}
