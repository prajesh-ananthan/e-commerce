package io.prajesh.bootstrap;

import io.prajesh.domain.*;
import io.prajesh.domain.security.Role;
import io.prajesh.enums.OrderStatus;
import io.prajesh.service.*;
import io.prajesh.util.JsonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

/**
 * Created by ANAN011 on 27/7/2017.
 *
 * @author Prajesh Ananthan, arvato Systems Malaysia Sdn Bhd
 *         <p>
 *         Bootstrap data for loading Customer and Product content
 */
@Component
public class SpringJPABootstrap implements ApplicationListener<ContextRefreshedEvent> {

  private static final Logger LOG = LoggerFactory.getLogger(SpringJPABootstrap.class);
  private ProductService productService;
  private CustomerService customerService;
  private UserService userService;
  private OrderService orderService;
  private RoleService roleService;
  @Value("${bootstrap.data}")
  private boolean initBootstrap;

  @Autowired
  public void setCustomerService(CustomerService customerService) {
    this.customerService = customerService;
  }

  @Autowired
  public void setProductService(ProductService productService) {
    this.productService = productService;
  }

  @Autowired
  public void setUserService(UserService userService) {
    this.userService = userService;
  }

  @Autowired
  public void setOrderService(OrderService orderService) {
    this.orderService = orderService;
  }

  @Autowired
  public void setRoleService(RoleService roleService) {
    this.roleService = roleService;
  }

  @Override
  public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
    if (initBootstrap) {
      try {
        loadProducts();
        loadUserAndCustomers();
        loadCarts();
        loadOrderHistory();
        loadRoles();
        applyDefaultRolesOnUsers();
      } catch (IOException e) {
        LOG.error("Unable to open JSON file: ", e.getMessage());
      }
    }
  }

  private void loadRoles() {
    Role role = new Role();
    role.setRole(CustomerService.CUSTOMER);
    roleService.saveOrUpdate(role);
  }

  private void applyDefaultRolesOnUsers() {
    final List<User> users = (List<User>) userService.list();
    final List<Role> roles = (List<Role>) roleService.list();

    roles.forEach(
        role -> {
          if (role.getRole().equalsIgnoreCase(CustomerService.CUSTOMER)) {
            users.forEach(
                user -> {
                  user.addRole(role);
                  userService.saveOrUpdate(user);
                }
            );
          }
        }
    );
  }

  private void loadOrderHistory() {
    final List<User> users = (List<User>) userService.list();
    final List<Product> products = (List<Product>) productService.list();

    users.forEach(user -> {
      Order order = new Order();
      order.setCustomer(user.getCustomer());
      order.setOrderStatus(OrderStatus.SHIPPED);

      products.forEach(product -> {
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setProduct(product);
        orderDetail.setQuantity(1);
        order.addToOrderDetails(orderDetail);
      });
      orderService.saveOrUpdate(order);
    });
  }

  private void loadCarts() {
    final List<User> users = (List<User>) userService.list();
    final List<Product> products = (List<Product>) productService.list();

    users.forEach(user -> {
      user.setCart(new Cart());
      CartDetail cartDetail = new CartDetail();
      cartDetail.setProduct(products.get(0));
      cartDetail.setQuantity(2);
      user.getCart().addCartDetail(cartDetail);
      userService.saveOrUpdate(user);
    });
  }

  private void loadProducts() throws IOException {
    List<Product> productList = JsonUtils.convertJsonToProductPojo(ProductService.PRODUCTS_JSON_FILE);
    productList.forEach(p -> productService.saveOrUpdate(p));
  }

  private void loadUserAndCustomers() throws IOException {
    List<User> userList = JsonUtils.convertJsonToUserPojo(UserService.USERS_JSON_FILE);
    userList.forEach(u -> userService.saveOrUpdate(u));
  }
}
