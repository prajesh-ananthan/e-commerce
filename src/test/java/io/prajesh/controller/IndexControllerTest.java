package io.prajesh.controller;

import org.junit.Before;
import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

/**
 * @author Prajesh Ananthan
 *         Created on 23/7/2017.
 */
public class IndexControllerTest {

  private MockMvc mockMvc;
  private IndexController indexController;

  @Before
  public void setUp() throws Exception {
    indexController = new IndexController();
    mockMvc = MockMvcBuilders.standaloneSetup(indexController).build();
  }

  @Test
  public void testIndexPage() throws Exception {
    mockMvc.perform(get("/"))
        .andExpect(status().isOk())
        .andExpect(view().name("index"));
  }
}