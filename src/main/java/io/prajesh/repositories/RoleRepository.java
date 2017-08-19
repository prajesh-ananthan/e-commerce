package io.prajesh.repositories;

import io.prajesh.domain.security.Role;
import org.springframework.data.repository.CrudRepository;

/**
 * @author Prajesh Ananthan
 *         Created on 17/8/2017.
 */
public interface RoleRepository extends CrudRepository<Role, Integer> {
}
