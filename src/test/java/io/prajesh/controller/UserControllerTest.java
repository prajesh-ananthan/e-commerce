package io.prajesh.controller;

import io.prajesh.domain.User;
import io.prajesh.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * @author Prajesh Ananthan
 *         Created on 14/8/2017.
 */
public class UserControllerTest {

  @Mock
  UserService userService;

  @InjectMocks
  UserController controller;

  private MockMvc mockMvc;

  @Before
  public void setUp() throws Exception {
    MockitoAnnotations.initMocks(this);
    mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
  }

  @Test
  public void testListUsers() throws Exception {
    // Given
    List<User> users = Arrays.asList(new User(), new User());
    when(userService.list()).thenReturn((List) users);

    // When and Verify
    mockMvc.perform(get("/user/list"))
        .andExpect(status().isOk())
        .andExpect(view().name(UserController.USERS_PAGE))
        .andExpect(model().attribute(UserController.USERS, hasSize(2)));
  }
}