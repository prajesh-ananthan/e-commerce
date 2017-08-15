package io.prajesh.controller;

import com.google.common.annotations.VisibleForTesting;
import io.prajesh.domain.User;
import io.prajesh.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Prajesh Ananthan
 *         Created on 9/8/2017.
 */
@Controller
@RequestMapping("/user")
public class UserController {

  @VisibleForTesting
  static final String USERS = "users";
  @VisibleForTesting
  static final String USER = "user";
  @VisibleForTesting
  static final String REDIRECT_USER_PAGE = "redirect:/" + USER + "/";
  @VisibleForTesting
  static final String REDIRECT_USER_LIST_PAGE = "redirect:/" + USER + "/list/";
  @VisibleForTesting
  static final String USER_FORM = "user/user-form";
  @VisibleForTesting
  static final String USER_PAGE = "user/user";
  @VisibleForTesting
  static final String USERS_PAGE = "user/users";
  private UserService userService;

  @Autowired
  public void setUserService(UserService userService) {
    this.userService = userService;
  }

  @GetMapping("/list")
  public String listCustomers(Model model) {
    model.addAttribute(USERS, userService.list());
    return USERS_PAGE;
  }

  @GetMapping("/{id}")
  public String findUserById(@PathVariable Integer id, Model model) {
    model.addAttribute(USER, userService.findById(id));
    return USER_PAGE;
  }

  @GetMapping("/new")
  public String createNewUser(Model model) {
    model.addAttribute(USER, new User());
    return USER_FORM;
  }

  @GetMapping("/edit/{id}")
  public String edit(@PathVariable Integer id, Model model) {
    User updateUser = userService.findById(id);
    model.addAttribute(USER, updateUser);
    return USER_FORM;
  }

  // TODO: Fix user deletion from the DB
  @RequestMapping(value = "/delete/{id}")
  public String delete(@PathVariable Integer id, Model model) {
    userService.remove(id);
    return REDIRECT_USER_LIST_PAGE;
  }

  @PostMapping
  public String createOrUpdateUser(User user) {
    User savedUser = userService.saveOrUpdate(user);
    return REDIRECT_USER_PAGE + savedUser.getId();
  }
}
