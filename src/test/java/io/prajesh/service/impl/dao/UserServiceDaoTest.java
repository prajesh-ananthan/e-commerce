package io.prajesh.service.impl.dao;

import io.prajesh.domain.*;
import io.prajesh.service.ProductService;
import io.prajesh.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.junit.Assert.*;

/**
 * @author Prajesh Ananthan
 *         Created on 30/7/2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles(AbstractDaoService.JPA_DAO)
public class UserServiceDaoTest {

  private UserService userService;
  private ProductService productService;
  private User user;

  @Autowired
  public void setUserService(UserService userService) {
    this.userService = userService;
    user = new User();
    user.setUserName("prajesh");
    user.setPassword("password");
  }

  @Autowired
  public void setProductService(ProductService productService) {
    this.productService = productService;
  }

  @Test
  public void testSaveOrUpdateUser() throws Exception {
    // Given

    // When
    User savedUser = userService.saveOrUpdate(user);

    // Verify
    assertEquals(savedUser.getUserName(), "prajesh");
    assertNotNull(user.getEncryptedPassword());
    System.out.println("Encrypted password ==> " + user.getEncryptedPassword());
  }

  @Test
  public void testSaveOfUserWithCustomer() throws Exception {
    // Given
    Customer customer = new Customer();
    customer.setFirstName("Prajesh");
    customer.setLastName("Ananthan");
    user.setCustomer(customer);

    // When
    User savedUser = userService.saveOrUpdate(user);

    // Verify
    assertNotNull(savedUser.getId());
    assert savedUser.getCustomer().getId() != null;
  }

  @Test
  public void testAddCartOnUser() throws Exception {
    // Given
    user.setCart(new Cart());

    // When
    User savedUser = userService.saveOrUpdate(user);

    // Verify
    assertNotNull(savedUser.getId());
    assertNotNull(savedUser.getCart());
  }

  @Test
  public void testAddCartToUserWithCartDetails() throws Exception {
    // Given
    // Set the cart for user
    user.setCart(new Cart());
    // Get the products
    List<Product> storedProducts = (List<Product>) productService.list();

    // Add product into CartDetail, Add CartDetail into Cart
    CartDetail cartItemOne = new CartDetail();
    cartItemOne.setProduct(storedProducts.get(0));
    user.getCart().addCartDetail(cartItemOne);

    CartDetail cartItemTwo = new CartDetail();
    cartItemTwo.setProduct(storedProducts.get(1));
    user.getCart().addCartDetail(cartItemTwo);

    // When
    User savedUser = userService.saveOrUpdate(user);

    // Verify
    assertNotNull(savedUser.getCart());
    assertNotNull(savedUser.getCart().getId());
    assertNotNull(savedUser.getCart().getCartDetails());
    assertThat(savedUser.getCart().getCartDetails(), hasSize(2));
  }

  @Test
  public void testRemoveCartToUserWithCartDetails() throws Exception {
    // Given
    user.setCart(new Cart());

    // When
    List<Product> storedProducts = (List<Product>) productService.list();

    // Given
    CartDetail cartItemOne = new CartDetail();
    cartItemOne.setProduct(storedProducts.get(0));
    user.getCart().addCartDetail(cartItemOne);

    CartDetail cartItemTwo = new CartDetail();
    cartItemTwo.setProduct(storedProducts.get(1));
    user.getCart().addCartDetail(cartItemTwo);

    // When
    User savedUser = userService.saveOrUpdate(user);

    // Verify
    assertThat(savedUser.getCart().getCartDetails(), hasSize(2));

    // Given
    savedUser.getCart().removeCartDetail(savedUser.getCart().getCartDetails().get(0));

    // When
    User updateUser = userService.saveOrUpdate(savedUser);

    // Verify
    assertThat(updateUser.getCart().getCartDetails(), hasSize(1));
  }
}