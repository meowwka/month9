package com.product.handmade.repo;

import com.product.handmade.model.Order;
import com.product.handmade.model.Place;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository <Order, Integer> {
}
