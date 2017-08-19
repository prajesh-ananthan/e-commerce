package io.prajesh.repositories;

import io.prajesh.domain.User;
import org.springframework.data.repository.CrudRepository;

/**
 * @author Prajesh Ananthan
 *         Created on 17/8/2017.
 */
public interface UserRepository extends CrudRepository<User, Integer> {
}
