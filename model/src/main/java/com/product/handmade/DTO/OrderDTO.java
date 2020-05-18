package com.product.handmade.DTO;


import com.product.handmade.model.Order;
import lombok.*;

@Getter
@Setter
@ToString
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class OrderDTO {
    private int id;
    private UserDTO user;
    private int number,zip;
    private String address,city,state;

    public static OrderDTO from(Order order){
        return builder().
                id(order.getId())
                .user(UserDTO.from(order.getUser()))
                .number(order.getNumber())
                .address(order.getAddress())
                .zip(order.getZip())
                .city(order.getCity())
                .state(order.getState())
                .build();
    }
}
