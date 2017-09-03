package io.ecommerce.service.security.events;

import io.ecommerce.domain.User;
import io.ecommerce.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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

  private UserService userService;

  @Autowired
  public void setUserService(UserService userService) {
    this.userService = userService;
  }

  @Override
  public void onApplicationEvent(LoginFailureEvent event) {
    Authentication authentication = (Authentication) event.getSource();
    updateUserAccount(authentication);
  }

  private void updateUserAccount(Authentication authentication) {
    User user = userService.findUserByUserName((String) authentication.getPrincipal());

    if (user != null) {
      user.setFailedLoginAttempts(user.getFailedLoginAttempts() + 1);
      log.debug("Failed login attempts for " + user.getUserName() + ": " + user.getFailedLoginAttempts());

      if (user.getFailedLoginAttempts() > 5) {
        user.setEnabled(false);
        log.error("### USER ACCOUNT IS LOCKED!");
      }
      userService.saveOrUpdate(user);
    }
  }
}
