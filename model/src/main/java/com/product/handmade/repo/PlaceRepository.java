package com.product.handmade.repo;

import com.product.handmade.model.Place;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface PlaceRepository extends JpaRepository <Place, Integer> {
}
