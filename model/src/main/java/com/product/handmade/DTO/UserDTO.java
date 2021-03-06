package com.product.handmade.DTO;

import com.product.handmade.model.User;
import lombok.*;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(access = AccessLevel.PRIVATE)
@Data
public class UserDTO {
    private int id;
    private String login;
    private String name;
    private String email;

    public static UserDTO from(User user) {
        return builder().id(user.getId())
                .email(user.getEmail())
                .name(user.getName())
                .login(user.getLogin())
                .build();

    }


}
