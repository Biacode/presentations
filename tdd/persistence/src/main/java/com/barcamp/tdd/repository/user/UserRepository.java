package com.barcamp.tdd.repository.user;

import com.barcamp.tdd.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Author: Arthur Asatryan
 * Company: SFL LLC
 * Date: 6/18/16
 * Time: 11:40 PM
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(final String email);
}
