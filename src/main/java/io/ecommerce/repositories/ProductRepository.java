package io.ecommerce.repositories;

import io.ecommerce.domain.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Prajesh Ananthan
 *         Created on 16/8/2017.
 */
@Repository
public interface ProductRepository extends CrudRepository<Product, Integer> {

}
