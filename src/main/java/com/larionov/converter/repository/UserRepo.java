package com.larionov.converter.repository;

import com.larionov.converter.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Service
@Repository
public interface UserRepo extends JpaRepository<User, Long> {
    User findByUsername(String email);
    User findByName(String name);
}
