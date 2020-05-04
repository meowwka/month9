package com.product.handmade.model;


import jdk.jfr.Name;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
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
    @Column(length = 50)
    private String name = "";

    @Size(min=5, max=15, message = "Login length must be >=5 and <=15")
    @Pattern(regexp = "^[^\\d\\s]+$", message = "should contain only letters")
    private String login = "";

    @NotBlank
    @Size(min = 3, max=15, message = "Password length must be >=3 and <=15")
    private String password = "";
}
