package io.ecommerce.controller;

import io.ecommerce.commands.CustomerForm;
import io.ecommerce.commands.validators.CustomerFormValidator;
import io.ecommerce.converters.CustomerToCustomerForm;
import io.ecommerce.domain.Address;
import io.ecommerce.domain.Customer;
import io.ecommerce.domain.User;
import io.ecommerce.service.CustomerService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.*;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

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
 *         Created on 25/7/2017.
 */
public class CustomerControllerTest {

  @Mock
  private CustomerService customerService;

  @InjectMocks
  private CustomerController customerController; // setups the controller, and inject mock objects into it

  private MockMvc mockMvc;

  @Before
  public void setUp() throws Exception {
    MockitoAnnotations.initMocks(this); // //initilizes controller and mocks
    customerController.setCustomerFormValidator(new CustomerFormValidator());
    customerController.setCustomerToCustomerForm(new CustomerToCustomerForm());
    mockMvc = MockMvcBuilders.standaloneSetup(customerController).build();
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

  @Test
  public void testSaveOrUpdate() throws Exception {
    // Passing the attributes inside

    // Given
    final Integer id = 1;
    Customer returnedCustomer = new Customer();
    String firstName = "Prajesh";
    String lastName = "Ananthan";
    String email = "prajesh.ananthan@ecommerce.com";
    String addressLine1 = "68, Jalan RJ 2/20";
    String addressLine2 = "Taman Rasah Jaya";
    String city = "Seremban";
    String state = "Negeri Sembilan";
    String zipCode = "70300";
    String phoneNumber = "60163456920";
    String userName = "pjesh";
    String password = "password";

    returnedCustomer.setId(id);
    returnedCustomer.setFirstName(firstName);
    returnedCustomer.setLastName(lastName);
    returnedCustomer.setEmail(email);
    returnedCustomer.setPhoneNumber(phoneNumber);
    returnedCustomer.setBillingAddress(new Address());
    returnedCustomer.getBillingAddress().setAddressLine1(addressLine1);
    returnedCustomer.getBillingAddress().setAddressLine1(addressLine2);
    returnedCustomer.getBillingAddress().setCity(city);
    returnedCustomer.getBillingAddress().setState(state);
    returnedCustomer.getBillingAddress().setZipCode(zipCode);
    returnedCustomer.setUser(new User());
    returnedCustomer.getUser().setUserName(userName);
    returnedCustomer.getUser().setPassword(password);

    when(customerService.saveOrUpdateCustomerForm(Matchers.<CustomerForm>any())).thenReturn(returnedCustomer);

    // When
    mockMvc.perform(
        post("/customer")
            .param("customerId", "1")
            .param("firstName", firstName)
            .param("lastName", lastName)
            .param("email", email)
            .param("phoneNumber", phoneNumber)
            .param("userName", userName)
            .param("passwordText", password)
            .param("passwordTextConfirm", password)
            .param("billingAddress.addressLine1", addressLine1)
            .param("billingAddress.addressLine2", addressLine2)
            .param("city", city)
            .param("state", state)
            .param("zipCode", zipCode))
        .andExpect(status().is3xxRedirection())
        .andExpect(view().name(CustomerController.REDIRECT_CUSTOMER_PAGE + id));

    // Verify
    // Check if customerForm is passed inside method
    ArgumentCaptor<CustomerForm> customerCaptor = ArgumentCaptor.forClass(CustomerForm.class);
    verify(customerService).saveOrUpdateCustomerForm(customerCaptor.capture());

    CustomerForm boundCustomer = customerCaptor.getValue();

    assertEquals(id, boundCustomer.getCustomerId());
    assertEquals(firstName, boundCustomer.getFirstName());
    assertEquals(lastName, boundCustomer.getLastName());
    assertEquals(email, boundCustomer.getEmail());
    assertEquals(phoneNumber, boundCustomer.getPhoneNumber());
  }

  /**
   * Check if the delete method is called with redirection status
   */
  @Test
  public void testDelete() throws Exception {
    // Given
    final Integer id = 1;

    // When
    mockMvc.perform(get("/customer/delete/1"))
        .andExpect(status().is3xxRedirection())
        .andExpect(view().name(CustomerController.REDIRECT_CUSTOMER_LIST));

    // Verify
    verify(customerService, times(1)).remove(id);
  }
}