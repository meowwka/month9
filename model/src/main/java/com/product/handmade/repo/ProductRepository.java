package com.product.handmade.repo;

import com.product.handmade.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository <Product, Integer> {
    Page<Product> findAllByPlaceId(int placeId, Pageable pageable);
    Page<Product> findAllByNameContains(String name, Pageable pageable);

}
