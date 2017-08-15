package io.prajesh.service.impl.dao;

import io.prajesh.domain.Cart;
import io.prajesh.domain.Customer;
import io.prajesh.domain.User;
import io.prajesh.service.CustomerService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by ANAN011 on 27/7/2017.
 *
 * @author Prajesh Ananthan, arvato Systems Malaysia Sdn Bhd
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@ActiveProfiles(AbstractDaoService.JPA_DAO)
public class CustomerServiceDaoTest {
  private CustomerService customerService;

  @Autowired
  public void setCustomerService(CustomerService customerService) {
    this.customerService = customerService;
  }

  @Test
  public void testList() throws Exception {
    List<Customer> customers = (List<Customer>) customerService.list();
    assertEquals(4, customers.size());
  }

  @Test
  public void testSaveUpdateUser() throws Exception {
    // Given
    Customer customer = new Customer();
    User user = new User();
    user.setUserName("john doe");
    user.setPassword("unknown");
    customer.setUser(user);

    // When
    Customer savedCustomer = customerService.saveOrUpdate(customer);

    // Verify
    assertNotNull(savedCustomer.getUser());
    assertEquals("john doe", savedCustomer.getUser().getUserName());
    assertNotNull(savedCustomer.getUser().getEncryptedPassword());
    System.out.println("Encrypted password ==> " + savedCustomer.getUser().getEncryptedPassword());
  }


}