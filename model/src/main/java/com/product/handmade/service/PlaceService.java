package com.product.handmade.service;

import com.product.handmade.DTO.PlaceDTO;
import com.product.handmade.exception.ResourceNotFoundException;
import com.product.handmade.repo.PlaceRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PlaceService {
    private final PlaceRepository placeRepository;

    public Page<PlaceDTO> getPlaces(Pageable pageable) {
        return placeRepository.findAll(pageable)
                .map(PlaceDTO::from);
        //.toList();
    }

    public PlaceDTO getPlace(int id) {
        var place = placeRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("place", id));
        return PlaceDTO.from(place);
    }
}
