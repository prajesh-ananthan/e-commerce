package io.ecommerce.service;

import io.ecommerce.domain.Order;
import org.springframework.stereotype.Service;

/**
 * @author Prajesh Ananthan
 *         Created on 6/8/2017.
 */
@Service
public interface OrderService extends CRUDService<Order> {
}
