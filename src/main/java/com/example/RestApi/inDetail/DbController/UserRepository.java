package com.example.RestApi.inDetail.DbController;

import com.example.RestApi.inDetail.UserDetails.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends
        JpaRepository<User, Integer> {
    public User findByName(String name);
    @Transactional
    public void deleteByName(String name);
}
