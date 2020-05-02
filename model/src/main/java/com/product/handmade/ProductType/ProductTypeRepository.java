package com.product.handmade.ProductType;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ProductTypeRepository extends JpaRepository <ProductType, Integer> {

    @Query("select p from ProductType as p where p.name like CONCAT(:name, '%')")
    public List<ProductType> getByName(String name);
}
