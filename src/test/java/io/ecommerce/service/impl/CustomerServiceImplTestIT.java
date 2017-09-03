package io.ecommerce.service.impl;

import io.ecommerce.domain.Customer;
import io.ecommerce.domain.User;
import io.ecommerce.service.CustomerService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

/**
 * @author Prajesh Ananthan
 *         Created on 3/9/2017.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class CustomerServiceImplTestIT {

  private CustomerService customerService;

  @Autowired
  public void setCustomerService(CustomerService customerService) {
    this.customerService = customerService;
  }

  @Test
  public void testList() throws Exception {
    // When
    List<Customer> customers = (List<Customer>) customerService.list();

    // Then
    assertThat(customers.size(), is((4)));
  }

  @Test
  public void testSaveUpdateUser() throws Exception {
    // Given
    Customer customer = new Customer();
    User user = new User();
    user.setUserName("divy");
    user.setPassword("password");
    customer.setUser(user);

    // When
    Customer savedCustomer = customerService.saveOrUpdate(customer);

    // Verify
    assertNotNull(savedCustomer.getUser());
    assertNotNull(savedCustomer.getUser().getId());
    assertEquals("divy", savedCustomer.getUser().getUserName());
  }
}