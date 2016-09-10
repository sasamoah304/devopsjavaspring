package com.devopsjavaspring.backend.persistence.repositories;

import com.devopsjavaspring.backend.persistence.domain.backend.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by stephenasamoah on 9/9/16.
 */
@Repository
public interface UserRepository extends CrudRepository<User,Long> {

    /**
     * Returns a User given a username or null if not found
     * @param username The username
     * @return
     */
    public User findByUsername(String username);
}
