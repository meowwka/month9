package edu.food.model.repo;

import edu.food.model.model.Food;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FoodRepository extends JpaRepository <Food, Integer> {
}
