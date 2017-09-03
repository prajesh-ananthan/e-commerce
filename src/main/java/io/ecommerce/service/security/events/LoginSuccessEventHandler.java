package io.ecommerce.service.security.events;

import io.ecommerce.domain.User;
import io.ecommerce.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class LoginSuccessEventHandler implements ApplicationListener<LoginSuccessEvent> {

  private UserService userService;

  @Autowired
  public void setUserService(UserService userService) {
    this.userService = userService;
  }

  @Override
  public void onApplicationEvent(LoginSuccessEvent event) {
    Authentication authentication = (Authentication) event.getSource();
    log.error("Login success for " + ((UserDetails) authentication.getPrincipal()).getUsername());
    updateUserAccount(authentication);
  }

  private void updateUserAccount(Authentication authentication) {
    UserDetails userDetails = (UserDetails) authentication.getPrincipal();
    User user = userService.findUserByUserName(userDetails.getUsername());

    if (user != null && user.getFailedLoginAttempts() > 0) {
      user.setFailedLoginAttempts(0);
      log.info("User account " + user.getUserName() + " has been unlocked");
      userService.saveOrUpdate(user);
    }
  }
}