package io.prajesh.util;

import io.prajesh.domain.Address;
import io.prajesh.domain.Customer;
import io.prajesh.domain.Product;
import io.prajesh.domain.User;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * @author Prajesh Ananthan
 *         Created on 22/7/2017.
 */
public class JsonUtilsTest {
  @Test
  public void convertJsonToPojoTest() throws Exception {
    // Given
    final String JSON_FILE = "products-test.json";

    // When
    final List<Product> products = JsonUtils.convertJsonToProductPojo(JSON_FILE);

    // Then
    assertThat(products.size(), is(4));
    assertEquals(products.get(0).getDescription(), "Product 1");
    assertEquals(products.get(1).getDescription(), "Product 2");
    assertEquals(products.get(2).getDescription(), "Product 3");
    assertEquals(products.get(3).getDescription(), "Product 4");
  }

  @Test
  public void convertJsonToCustomerPojoTest() throws Exception {
    // Given
    final String JSON_FILE = "customer-test.json";

    // When
    final List<Customer> customers = JsonUtils.convertJsonToCustomerPojo(JSON_FILE);
    final Address address = customers.get(0).getBillingAddress();
    final User user = customers.get(0).getUser();

    // Verify
    assertNotNull(customers);
    assertNotNull(address);
    assertNotNull(user);
    assertThat(customers.size(), is(4));
    assertEquals(address.getAddressLine1(), "50, Jalan RJ, 1/21");
    assertEquals(address.getCity(), "Seremban");
    assertEquals(address.getAddressLine2(), "Taman Rasah Jaya");
    assertEquals(address.getState(), "Negeri Sembilan");
    assertEquals(address.getZipCode(), "70300");
    assertEquals(user.getUserName(), "pjesh");
    assertEquals(user.getPassword(), "password");
  }
}