package com.juliocesar.webpage.repository;

import com.juliocesar.webpage.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    public User findByUsernameOrEmail(String username, String email);

}
