package io.ecommerce.converters;

import io.ecommerce.domain.User;
import io.ecommerce.service.security.UserDetailsImpl;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;

/**
 * @author Prajesh Ananthan
 *         Created on 28/8/2017.
 */
@Component
public class UserToUserDetails implements Converter<User, UserDetails> {

  @Override
  public UserDetails convert(User user) {
    UserDetailsImpl userDetails = new UserDetailsImpl();

    if (user != null) {
      userDetails.setUsername(user.getUserName());
      userDetails.setPassword(user.getEncryptedPassword());
      userDetails.setEnabled(user.getEnabled());

      Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
      user.getRoles().forEach(role -> authorities.add(new SimpleGrantedAuthority(role.getRole())));
      userDetails.setAuthorities(authorities);
    }
    return userDetails;
  }
}
