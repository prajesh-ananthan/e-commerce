package io.ecommerce.service;

import io.ecommerce.domain.User;
import org.springframework.stereotype.Service;

/**
 * @author Prajesh Ananthan
 *         Created on 30/7/2017.
 */
@Service
public interface UserService extends CRUDService<User> {
  String USERS_JSON_FILE = "json/users.json";
  String ADMIN = "ADMIN";

  User findUserByUserName(String userName);
}
