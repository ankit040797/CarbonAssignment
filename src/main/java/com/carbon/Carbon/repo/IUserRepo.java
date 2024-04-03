package com.carbon.Carbon.repo;

import com.carbon.Carbon.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IUserRepo extends JpaRepository<User,Integer> {
    Optional<User> findByEmail(String email);
}
