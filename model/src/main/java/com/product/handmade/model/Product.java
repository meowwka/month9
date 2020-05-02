package com.product.handmade.model;

import lombok.*;

import javax.persistence.*;

@Data
@Builder
@AllArgsConstructor(access = AccessLevel.PACKAGE) @NoArgsConstructor
@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 128)
    private String name;

    @Column(length = 128)
    private String image;

    @ManyToOne
    @JoinColumn(name = "product_type_id")
    private ProductType type;

    @Column
    private float price;

    @ManyToOne(fetch = FetchType.LAZY)
    private Place place;
}
