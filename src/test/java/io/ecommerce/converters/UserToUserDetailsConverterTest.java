package io.ecommerce.converters;

import io.ecommerce.domain.User;
import io.ecommerce.domain.security.Role;
import org.junit.Before;
import org.junit.Test;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.userdetails.UserDetails;

import static org.junit.Assert.assertEquals;

/**
 * @author Prajesh Ananthan
 *         Created on 3/9/2017.
 */
public class UserToUserDetailsConverterTest {

  Converter<User, UserDetails> converter;

  @Before
  public void setUp() throws Exception {
    converter = new UserToUserDetails();
  }

  @Test
  public void testConvert() throws Exception {
    // Given
    String userName = "pjesh";
    String password = "password";
    String roleName1 = "USER";
    String roleName2 = "ADMIN";

    Role role1 = new Role();
    role1.setRole(roleName1);

    Role role2 = new Role();
    role2.setRole(roleName2);

    User user = new User();
    user.setUserName(userName);
    user.setPassword(password);
    user.setEncryptedPassword(password);

    user.addRole(role1);
    user.addRole(role2);

    // When
    UserDetails userDetails = converter.convert(user);

    // Verify
    assertEquals(userName, userDetails.getUsername());
    assertEquals(password, userDetails.getPassword());
//    assertThat(userDetails.getAuthorities().size(), is(2)); TODO
  }
}