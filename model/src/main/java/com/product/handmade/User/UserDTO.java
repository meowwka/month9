package com.product.handmade.User;

import lombok.*;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(access = AccessLevel.PRIVATE)
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
