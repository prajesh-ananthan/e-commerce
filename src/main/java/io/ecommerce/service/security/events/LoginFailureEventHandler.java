package io.ecommerce.service.security.events;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

/**
 * @author Prajesh Ananthan
 *         Created on 3/9/2017.
 */
@Component
@Slf4j
public class LoginFailureEventHandler implements ApplicationListener<LoginFailureEvent> {

  @Override
  public void onApplicationEvent(LoginFailureEvent event) {
    Authentication authentication = (Authentication) event.getSource();
    log.error("Login failure for user: " + authentication.getPrincipal());
  }
}
