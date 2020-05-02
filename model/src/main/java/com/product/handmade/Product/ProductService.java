package com.product.handmade.Product;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ProductService {

    private final ProductRepository foodRepository;

    public Page<ProductDTO> getFoods(int id, Pageable pageable) {
        return foodRepository.findAllByPlaceId(id, pageable)
                .map(ProductDTO::from);
    }
}
