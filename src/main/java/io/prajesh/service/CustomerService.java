package io.prajesh.service;

import io.prajesh.domain.Customer;
import org.springframework.stereotype.Service;

/**
 * @author Prajesh Ananthan
 *         Created on 23/7/2017.
 */
@Service
public interface CustomerService extends CRUDService<Customer> {
  String CUSTOMERS_JSON_FILE = "json/customers.json";
  String CUSTOMER = "CUSTOMER";
}
