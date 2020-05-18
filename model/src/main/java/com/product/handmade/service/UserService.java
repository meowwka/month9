package com.product.handmade.service;

import com.product.handmade.DTO.UserDTO;
import com.product.handmade.exception.CustomerAlreadyRegisteredException;
import com.product.handmade.exception.CustomerNotFoundException;
import com.product.handmade.model.User;
import com.product.handmade.model.UserRegisterForm;
import com.product.handmade.repo.UserRepo;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {
    private UserRepo userRepository;
    private final PasswordEncoder encoder;


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
    public boolean check(String email){
        return userRepository.existsByEmail(email);
    }

    public UserDTO register(UserRegisterForm userRegisterForm){
        if(userRepository.existsByEmail(userRegisterForm.getEmail())){
            throw new CustomerAlreadyRegisteredException();
        }

        var user = User.builder()
                .email(userRegisterForm.getEmail())
                .name(userRegisterForm.getName())
                .login(userRegisterForm.getLogin())
                .password(encoder.encode(userRegisterForm.getPassword()))
                .build();

        userRepository.save(user);
        return UserDTO.from(user);
    }

    public UserDTO getByEmail(String email){
        var user = userRepository.findByEmail(email)
                .orElseThrow(CustomerNotFoundException::new);
        return UserDTO.from(user);
    }
}
