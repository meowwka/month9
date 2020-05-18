package com.product.handmade.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Data
@Table(name="orders")
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PACKAGE)
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "user_id")
    private User user ;

    @NotBlank(message = "Cannot be null")
    @Column(length = 128)
    private int number,zip;

    @NotBlank(message = "Cannot be null")
    @Column(length = 128)
    private String address,city,state;



}
