package io.ecommerce.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by ANAN011 on 20/7/2017.
 *
 * @author Prajesh Ananthan, arvato Systems Malaysia Sdn Bhd
 */
@Controller
public class IndexController {

  private static final String INDEX_PAGE = "index";
  private static final String LOGIN_PAGE = "login";
  private static final String ACCESS_DENIED = "access_denied";

  @RequestMapping("/")
  public String home() {
    return INDEX_PAGE;
  }

  @RequestMapping("/access_denied")
  public String notAuth() {
    return ACCESS_DENIED;
  }

  @RequestMapping("/login")
  public String loginForm() {
    return LOGIN_PAGE;
  }
}