package com.product.handmade.DTO;

import com.product.handmade.model.User;
import lombok.*;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(access = AccessLevel.PRIVATE) @ToString
@Getter
@Setter
public class UserDTO {
    private int id;
    private String login;
    private String email;

    static UserDTO from(User user) {
        return builder().id(user.getId())
                .email(user.getEmail())
                .login(user.getLogin())
                .build();

    }


}
