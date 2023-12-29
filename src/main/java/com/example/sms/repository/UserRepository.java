package com.example.sms.repository;

import com.example.sms.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);

    @Query("select u.username from User u where u.username <> ?1")
    List<String> findAllUsersExcept(String username);
}
