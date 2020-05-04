package com.product.handmade.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotBlank
    @Column(length = 128)
    private String email;

    @NotBlank
    @Column(length = 50)
    private String name;

    @NotBlank
    @Size(min=5, max=15, message = "Login must be >=5 and <= 15 symbols ")
    @Column(length = 15)
    @Pattern(regexp = "^[^\\d\\s]+$", message = "should contain only letters")
    private String login;

    @NotBlank
    @Size(min=3, max=15, message = "Password must be  >=3 and <= 15 symbols")
    @Column(length = 15)
    private String password;

}
