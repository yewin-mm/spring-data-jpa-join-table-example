package com.yewin.jpajointableexample.repository;

import com.yewin.jpajointableexample.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @Author Ye Win.
 * @CreatedDate 02/07/2019.
 * @Description This class is to do database operation.
 */

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
