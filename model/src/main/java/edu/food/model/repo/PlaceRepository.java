package edu.food.model.repo;

import edu.food.model.model.Place;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlaceRepository extends JpaRepository <Place, Integer> {
}
