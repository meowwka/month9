package com.product.handmade.controller;

import com.product.handmade.DTO.PlaceDTO;
import com.product.handmade.DTO.ProductDTO;
import com.product.handmade.service.PlaceService;
import com.product.handmade.service.ProductService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Min;
import java.util.List;

@RestController
@RequestMapping("/api/places")
@AllArgsConstructor(access = AccessLevel.PRIVATE)
class PlaceRestController {
    private final PlaceService placeService;
    private final ProductService  productService;

    @GetMapping
    public List<PlaceDTO> getPlaces(Pageable pageable) {
        return placeService.getPlaces(pageable).getContent();
    }
    @GetMapping("productAll")
    public List<ProductDTO> getProducts(Pageable pageable){
        return productService.findProducts(pageable).getContent();
    }

    @GetMapping("/{id:\\d+?}")
    public PlaceDTO placePage(@PathVariable int id) {
        return placeService.getPlace(id);
    }

    @GetMapping("/{id:\\d+}/products")
    public List<ProductDTO> getFoods(@PathVariable @Min(5) int id, Pageable pageable) {
        return productService.getProducts(id, pageable).getContent();
    }
}
