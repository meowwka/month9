package edu.food.model.repo;

import edu.food.model.model.ProductType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductTypeRepository extends JpaRepository <ProductType, Integer> {

    @Query("select p from ProductType as p where p.name like CONCAT(:name, '%')")
    public List<ProductType> getByName(String name);
}
