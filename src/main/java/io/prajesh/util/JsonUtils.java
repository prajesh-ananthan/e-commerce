package io.prajesh.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.prajesh.domain.Customer;
import io.prajesh.domain.Product;
import io.prajesh.domain.User;
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