package io.ecommerce.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.ecommerce.domain.Customer;
import io.ecommerce.domain.Product;
import io.ecommerce.domain.User;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * @author Prajesh Ananthan
 *         Created on 22/7/2017.
 */
public class JsonUtils {
  public static List<Product> convertJsonToProductPojo(String file) throws IOException {
    return Arrays.asList(new ObjectMapper().readValue(new ClassPathResource(file).getInputStream(), Product[].class));
  }

  public static List<Customer> convertJsonToCustomerPojo(String file) throws IOException {
    return Arrays.asList(new ObjectMapper().readValue(new ClassPathResource(file).getInputStream(), Customer[].class));
  }

  public static List<User> convertJsonToUserPojo(String file) throws IOException {
    return Arrays.asList(new ObjectMapper().readValue(new ClassPathResource(file).getInputStream(), User[].class));
  }
}