package io.ecommerce.service.security;

import io.ecommerce.domain.User;
import io.ecommerce.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author Prajesh Ananthan
 *         Created on 28/8/2017.
 */
@Service("userDetailsService") // TODO: SpringSecurityConfig
public class SpringSecurityUserDetailsService implements UserDetailsService {

  private UserService userService;
  private Converter<User, UserDetails> userUserDetailsConverter;

  @Autowired
  public void setUserService(UserService userService) {
    this.userService = userService;
  }

  @Autowired
  @Qualifier(value = "userToUserDetails") // TODO: Try removing this
  public void setUserUserDetailsConverter(Converter<User, UserDetails> userUserDetailsConverter) {
    this.userUserDetailsConverter = userUserDetailsConverter;
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    return userUserDetailsConverter.convert(userService.findUserByUserName(username));
  }
}
