package com.product.handmade.service;

import com.product.handmade.DTO.ProductDTO;
import com.product.handmade.repo.ProductRepository;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ProductService {

    private final ProductRepository productRepository;

    public Page<ProductDTO> getProducts(int id, Pageable pageable) {
        return productRepository.findAllByPlaceId(id, pageable)
                .map(ProductDTO::from);
    }
    public Page<ProductDTO> findByName(String name, Pageable pageable){
        return productRepository.findAllByNameContains(name,pageable).map(ProductDTO::from);
    }
    public Page<ProductDTO> findProducts(Pageable pageable){
        return productRepository.findAll(pageable).map(ProductDTO::from);
    }
    public List<ProductDTO> findAllProducts() {
        return productRepository.findAll().stream()
                .map(ProductDTO::from).collect(Collectors.toList());
    }
}

