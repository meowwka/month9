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
    @NotBlank
    @Email
    private String email = "";

    @NotBlank
    @Size(min=3, max=128)
    @Pattern(regexp = "^[^\\d\\s]+$")
    private String name = "";

    @NotBlank
    @Size(min=3, max=128)
    private String login = "";

    @NotBlank
    @Size(min=3, max=128)
    private String password = "";
}
