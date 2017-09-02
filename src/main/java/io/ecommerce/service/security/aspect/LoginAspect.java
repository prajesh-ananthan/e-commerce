package io.ecommerce.service.security.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.*;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

/**
 * @author Prajesh Ananthan
 *         Created on 3/9/2017.
 */

@Aspect
@Component
@Slf4j
public class LoginAspect {
  @Pointcut("execution(* org.springframework.security.authentication.AuthenticationProvider.authenticate(..))")
  public void doAuthenticate() {
  }

  @Before("io.ecommerce.service.security.aspect.LoginAspect.doAuthenticate() && args(authentication)")
  public void logBefore(Authentication authentication) {
    log.debug("This is before the Authentication Method authentication " + authentication.isAuthenticated());
  }

  @AfterReturning(value = "io.ecommerce.service.security.aspect.LoginAspect.doAuthenticate()",
      returning = "authentication")
  public void logAfterAuthenticate(Authentication authentication) {
    log.debug("This is after the Authentication Method authentication " + authentication.isAuthenticated());
  }

  @AfterThrowing("io.ecommerce.service.security.aspect.LoginAspect.doAuthenticate() && args(authentication)")
  public void logAuthenicationException(Authentication authentication) {
    String userDetails = (String) authentication.getPrincipal();
    log.error("Login failed for user: " + userDetails);
  }
}