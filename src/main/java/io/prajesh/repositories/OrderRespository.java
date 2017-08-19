package io.prajesh.repositories;

import io.prajesh.domain.Order;
import org.springframework.data.repository.CrudRepository;

/**
 * @author Prajesh Ananthan
 *         Created on 17/8/2017.
 */
public interface OrderRespository extends CrudRepository<Order, Integer> {
}
