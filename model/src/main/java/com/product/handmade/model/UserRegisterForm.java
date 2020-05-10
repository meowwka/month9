package com.product.handmade.model;


import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@Setter
public class UserRegisterForm {
    @NotBlank(message = "Email is not correct")
    @Email
    private String email = "";

    @NotBlank
    @Size(min=3, max=128, message = "Name cannot be empty and less than 3 symbols")
    @Pattern(regexp = "^[^\\d\\s]+$", message = "Should contain only letters")
    private String name = "";

    @NotBlank
    @Size(min=3, max=128, message = "Login cannot be empty and less than 3 symbols")
    private String login = "";

    @NotBlank
    @Size(min=4, max=128,message = "Password cannot be empty and less than 4 symbols")
    private String password = "";
}
