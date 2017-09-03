package io.ecommerce.service.security.aspect;

import io.ecommerce.service.security.events.LoginFailureEvent;
import io.ecommerce.service.security.events.LoginFailureEventPublisher;
import io.ecommerce.service.security.events.LoginSuccessEvent;
import io.ecommerce.service.security.events.LoginSuccessEventPublisher;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
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

  private LoginFailureEventPublisher failureEventPublisher;
  private LoginSuccessEventPublisher successEventPublisher;

  @Autowired
  public void setFailureEventPublisher(LoginFailureEventPublisher failureEventPublisher) {
    this.failureEventPublisher = failureEventPublisher;
  }

  @Autowired
  public void setSuccessEventPublisher(LoginSuccessEventPublisher successEventPublisher) {
    this.successEventPublisher = successEventPublisher;
  }

  @Pointcut("execution(* org.springframework.security.authentication.AuthenticationProvider.authenticate(..))")
  public void doAuthenticate() {
  }

  @Before("io.ecommerce.service.security.aspect.LoginAspect.doAuthenticate() && args(authentication)")
  public void logBefore(Authentication authentication) {
  }

  @AfterReturning(value = "io.ecommerce.service.security.aspect.LoginAspect.doAuthenticate()",
      returning = "authentication")
  public void logAfterAuthenticate(Authentication authentication) {
    successEventPublisher.publish(new LoginSuccessEvent((authentication)));
  }

  @AfterThrowing("io.ecommerce.service.security.aspect.LoginAspect.doAuthenticate() && args(authentication)")
  public void logAuthenicationException(Authentication authentication) {
    String username = (String) authentication.getPrincipal();
    log.error("Login failed for user: " + username);
    failureEventPublisher.publish(new LoginFailureEvent(authentication));
  }
}