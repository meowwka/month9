package com.product.handmade.model;

import com.product.handmade.cart.Cart;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Set;

@Data
@Entity
@Table(name = "users")
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PACKAGE)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Email
    @NotBlank(message = "Email cannot be Empty")
    @Column(length = 128)
    private String email;

    @NotBlank(message = "Name cannot be empty")
    @Size(min=3, max=128, message = "Name should be more than 3 symbols")
    @Column(length = 128)
    @Pattern(regexp = "^[^\\d\\s]+$", message = "should contain only letters")
    private String name;

    @NotBlank(message = "Login cannot be empty")
    @Size(min=3, max=128, message = "Login should be more than 3 symbols ")
    @Column(length = 128)
    private String login;

    @NotBlank(message = "Password cannot be empty")
    @Size(min=8, max=128, message = "Password must be more than 8 symbols")
    @Column(length = 128)
    private String password;

    @Column
    @Builder.Default
    private boolean enabled = true;

    @NotBlank
    @Size(min=1, max = 60)
    @Column(length = 60)
    @Builder.Default
    private String role ="USER";

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "cart_id", referencedColumnName = "id")
    private Set<Cart> cart;
}
