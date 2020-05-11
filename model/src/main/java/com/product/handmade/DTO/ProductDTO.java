package com.product.handmade.DTO;

import com.product.handmade.model.Place;
import com.product.handmade.model.ProductType;
import com.product.handmade.model.Product;
import lombok.*;

@Getter
@Setter
@ToString
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ProductDTO {

    private int id;
    private String name;
    private String image;
    private ProductTypeDTO type;
    private float price;
    private PlaceDTO place;

    public static ProductDTO from(Product food) {
        return builder()
                .id(food.getId())
                .name(food.getName())
                .type(ProductTypeDTO.from(food.getType()))
                .image(food.getImage())
                .price(food.getPrice())
                .place(PlaceDTO.from(food.getPlace()))
                .build();
    }

    @Getter
    @Setter
    @ToString
    @Builder(access = AccessLevel.PRIVATE)
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class ProductTypeDTO {
        private int id;
        private String name;
        private String icon;

        public static ProductTypeDTO from(ProductType pType) {
            return builder()
                    .id(pType.getId())
                    .name(pType.getName())
                    .icon(pType.getIcon())
                    .build();
        }
    }
}
