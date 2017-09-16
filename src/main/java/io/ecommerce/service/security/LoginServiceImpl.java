package io.ecommerce.service.security;

import io.ecommerce.domain.User;
import io.ecommerce.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Prajesh Ananthan
 *         Created on 3/9/2017.
 */
@Service
@Slf4j
public class LoginServiceImpl implements LoginService {

  private UserService userService;

//  @Value("${user.lock.duration}")
//  private long duration;

  @Autowired
  public void setUserService(UserService userService) {
    this.userService = userService;
  }

  @Scheduled(fixedRate = 60000)
  @Override
  public void resetFailedLogins() {
    log.info("Starting scheduler. Checking for locked user accounts");

    List<User> userList = (List<User>) userService.list();

    userList.forEach(user -> {
      if (!user.getEnabled() && user.getFailedLoginAttempts() > 5) {
        user.setEnabled(true);
        user.setFailedLoginAttempts(0);
        userService.saveOrUpdate(user);
      }
    });
    log.info("User accounts are unlocked");
  }
}
