package io.ecommerce.controller;

import io.ecommerce.commands.ProductForm;
import io.ecommerce.domain.Product;
import io.ecommerce.service.ProductService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.*;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.instanceOf;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * @author Prajesh Ananthan
 *         Created on 26/7/2017.
 */
public class ProductControllerTest {

  @Mock
  private ProductService productService;

  @InjectMocks
  private ProductController productController;

  private MockMvc mockMvc;

  @Before
  public void setUp() throws Exception {
    MockitoAnnotations.initMocks(this);
    mockMvc = MockMvcBuilders.standaloneSetup(productController).build();
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

  @Test
  public void testSaveOrUpdate() throws Exception {
    // Given
    Product returnedProduct = new Product();
    Integer id = 1;
    String description = "This is a test product";
    BigDecimal price = new BigDecimal("20.00");
    String imageUrl = "http://test-product.com";

    returnedProduct.setId(id);
    returnedProduct.setDescription(description);
    returnedProduct.setPrice(price);
    returnedProduct.setImageUrl(imageUrl);

    when(productService.saveOrUpdateProductForm(Matchers.<ProductForm>any())).thenReturn(returnedProduct);

    // When
    mockMvc.perform(post("/product")
        .param("id", "1")
        .param("description", description)
        .param("price", "20.00")
        .param("imageUrl", imageUrl))
        .andExpect(status().is3xxRedirection())
        .andExpect(view().name(ProductController.REDIRECT_PRODUCT_PAGE + id));

    // Verify
    ArgumentCaptor<ProductForm> productCaptor = ArgumentCaptor.forClass(ProductForm.class);
    verify(productService).saveOrUpdateProductForm(productCaptor.capture());

    ProductForm boundProduct = productCaptor.getValue();

    assertEquals(id, boundProduct.getId());
    assertEquals(description, boundProduct.getDescription());
    assertEquals(price, boundProduct.getPrice());
    assertEquals(imageUrl, boundProduct.getImageUrl());
  }

  @Test
  public void testShowProduct() throws Exception {
    // Given
    final Integer id = 1;
    when(productService.findById(id)).thenReturn(new Product());

    // When and Verify
    mockMvc.perform(get("/product/1"))
        .andExpect(status().isOk())
        .andExpect(view().name(ProductController.PRODUCT_PAGE))
        .andExpect(model().attribute(ProductController.PRODUCT, instanceOf(Product.class)));
  }

  @Test
  public void testDelete() throws Exception {
    // Given
    final Integer id = 1;

    // When
    mockMvc.perform(get("/product/delete/1"))
        .andExpect(status().is3xxRedirection())
        .andExpect(view().name(ProductController.REDIRECT_PRODUCT_LIST));

    // Verify
    verify(productService, times(1)).remove(id);
  }
}