package com.devopsjavaspring.backend.persistence.repositories;

import com.devopsjavaspring.backend.persistence.domain.backend.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by stephenasamoah on 9/9/16.
 */
@Repository
public interface RoleRepository extends CrudRepository<Role, Integer> {
}
