package com.product.handmade.service;

import com.product.handmade.model.User;
import com.product.handmade.model.UserRegisterForm;
import com.product.handmade.repo.UserRepo;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UserService {
    private UserRepo userRepository;

    public void saveUser(UserRegisterForm form) {
        User u = new User();
        u.setLogin(form.getLogin());
        u.setEmail(form.getEmail());
        u.setName(form.getName());
        u.setPassword(form.getPassword());
        userRepository.save(u);
    }

    public boolean checkUser(UserRegisterForm form) {
        return userRepository.existsByLoginAndEmail(form.getLogin(), form.getEmail());
    }
}
