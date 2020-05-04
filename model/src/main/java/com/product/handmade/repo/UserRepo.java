package com.product.handmade.repo;


import com.product.handmade.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Integer> {
    boolean existsByLoginAndEmail(String login, String email);

}
