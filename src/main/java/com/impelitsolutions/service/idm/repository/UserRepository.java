package com.impelitsolutions.service.idm.repository;

import com.impelitsolutions.service.idm.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    @Query(value = "select * from user_info where username ilike ?1 limit 1", nativeQuery = true)
    Optional<User> findUserByUsername(String username);
}
