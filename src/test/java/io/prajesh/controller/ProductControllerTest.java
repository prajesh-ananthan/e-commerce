package io.prajesh.controller;

import io.prajesh.domain.Product;
import io.prajesh.service.ProductService;
import org.junit.Before;
import org.junit.Ignore;
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
 *         Created on 26/7/2017.
 */
public class ProductControllerTest {

  @Mock
  private ProductService productService;

  @InjectMocks
  private ProductController controller;

  private MockMvc mockMvc;

  @Before
  public void setUp() throws Exception {
    MockitoAnnotations.initMocks(this);
    mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
  }

  @Test
  public void testListProducts() throws Exception {
    // Given
    final List<Product> products = Arrays.asList(new Product(), new Product());

    when(productService.list()).thenReturn((List) products);

    // Verify
    mockMvc.perform(get("/product/list"))
        .andExpect(status().isOk())
        .andExpect(view().name(ProductController.PRODUCTS_PAGE))
        .andExpect(model().attribute(ProductController.PRODUCTS, hasSize(2)));

  }

  @Ignore
  @Test
  public void testCreateOrUpdateProduct() throws Exception {
    // TODO
  }

  @Ignore
  @Test
  public void testShowProduct() throws Exception {
    // TODO
  }

  @Ignore
  @Test
  public void testDelete() throws Exception {
    // TODO
  }
}