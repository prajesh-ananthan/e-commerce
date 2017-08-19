package io.prajesh.repositories;

import io.prajesh.domain.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Prajesh Ananthan
 *         Created on 17/8/2017.
 */
@Repository
public interface CustomerRepository extends CrudRepository<Customer, Integer> {
}
