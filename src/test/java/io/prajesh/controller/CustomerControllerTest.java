package io.prajesh.controller;

import io.prajesh.domain.Customer;
import io.prajesh.service.CustomerService;
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

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * @author Prajesh Ananthan
 *         Created on 25/7/2017.
 */
public class CustomerControllerTest {

  @Mock
  private CustomerService customerService;

  @InjectMocks
  private CustomerController controller; // setups the controller, and inject mock objects into it

  private MockMvc mockMvc;

  @Before
  public void setUp() throws Exception {
    MockitoAnnotations.initMocks(this); // //initilizes controller and mocks
    mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
  }

  @Test
  public void testListCustomers() throws Exception {
    // Given
    final List<Customer> customers = Arrays.asList(new Customer(), new Customer());

    when(customerService.list()).thenReturn((List) customers);

    // Verify
    mockMvc.perform(get("/customer/list"))
        .andExpect(status().isOk())
        .andExpect(view().name(CustomerController.CUSTOMERS_PAGE))
        .andExpect(model().attribute(CustomerController.CUSTOMERS, hasSize(2)));

  }

  @Test
  public void testShowCustomer() throws Exception {
    // Given
    final Integer id = 1;
    when(customerService.findById(id)).thenReturn(new Customer());

    // Verify
    mockMvc.perform(get("/customer/1"))
        .andExpect(status().isOk())
        .andExpect(view().name(CustomerController.CUSTOMER_PAGE))
        .andExpect(model().attribute(CustomerController.CUSTOMER, instanceOf(Customer.class)));
  }

  @Ignore
  @Test
  public void testCreateOrUpdateCustomer() throws Exception {
    // Passing the attributes inside

    // Given
    String id = "1";
    String firstName = "Prajesh";
    String lastName = "Ananthan";
    String email = "prajesh.ananthan@test.com";
    String phoneNumber = "01812345678";
    String address = "30, Jalan RJ 1/1";
    String city = "Seremban";
    String state = "Negeri Sembilan";
    String zipCode = "70300";


//    Customer returnedCustomer = new Customer();
//    returnedCustomer.setId(Integer.parseInt(id));
//    returnedCustomer.setFirstName(firstName);
//    returnedCustomer.setLastName(lastName);
//    returnedCustomer.setEmail(email);
//    returnedCustomer.setPhoneNumber(phoneNumber);
//    returnedCustomer.setAddress(address);
//    returnedCustomer.setCity(city);
//    returnedCustomer.setState(state);
//    returnedCustomer.setZipCode(Integer.parseInt(zipCode));

    // TODO
//    when(customerService.saveOrUpdate(Matchers.<Customer>any())).thenReturn(returnedCustomer);


    // When
    mockMvc.perform(
        post("/customer")
            .param("id", id)
            .param("firstName", firstName)
            .param("lastName", lastName)
            .param("email", email)
            .param("phoneNumber", phoneNumber)
            .param("address", address)
            .param("city", city)
            .param("state", state)
            .param("zipCode", zipCode)
    )
        .andExpect(status().is3xxRedirection())
        .andExpect(view().name(CustomerController.REDIRECT_CUSTOMER_PAGE + id))
        .andExpect(model().attribute("customer", instanceOf(Customer.class)))
        .andExpect(model().attribute("customer", hasProperty("id", is(id))))
        .andExpect(model().attribute("customer", hasProperty("firstName", is(firstName))));


  }

  /**
   * Check if the delete method is called with redirection status
   */
  @Test
  public void testDelete() throws Exception {
    // Given
    Integer id = 1;

    // When
    mockMvc.perform(get("/customer/delete/1"))
        .andExpect(status().is3xxRedirection())
        .andExpect(view().name(CustomerController.REDIRECT_CUSTOMER_LIST));

    // Verify
    verify(customerService, times(1)).remove(id);
  }
}