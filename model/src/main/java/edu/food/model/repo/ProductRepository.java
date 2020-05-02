package edu.food.model.repo;

import edu.food.model.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository <Product, Integer> {
}
