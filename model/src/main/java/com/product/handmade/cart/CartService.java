package com.product.handmade.cart;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CartService {
    private CartRepository repository;

    public void saveCart(Cart c) {
        repository.save(c);
    }
}
