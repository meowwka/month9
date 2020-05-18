package com.product.handmade.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "product_types")
public class ProductType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(length = 128)
    private String name;
    @Column(length = 128)
    private String icon;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "type")
    @OrderBy("name ASC")
    List<Product> products;
}
