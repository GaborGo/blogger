package com.blgr.blogapp.repository;

import com.blgr.blogapp.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);

   Optional<User> findById(Long id);

    User findByEmail(String emmail);

    List<User> findAll();

    void deleteUserById(Long id);


}
