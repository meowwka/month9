package com.product.handmade.service;

import com.product.handmade.DTO.ProductDTO;
import com.product.handmade.repo.ProductRepository;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ProductService {

    private final ProductRepository foodRepository;

    public Page<ProductDTO> getProducts(int id, Pageable pageable) {
        return foodRepository.findAllByPlaceId(id, pageable)
                .map(ProductDTO::from);
    }
}
