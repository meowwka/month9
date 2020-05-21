package com.product.handmade.model;

import com.product.handmade.cart.Cart;
import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor(access = AccessLevel.PACKAGE) @NoArgsConstructor
@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(length = 128)
    private String name;

    @Column(length = 128)
    private String image;

    @ManyToOne
    @JoinColumn(name = "product_type_id")
    private ProductType type;

    @Column
    private float price;

    @ManyToOne
    @JoinColumn(name = "place_id")
    private Place place;

    @ManyToMany(mappedBy = "products")
    List<Cart> carts;

    @Override
    public String toString() {
        return String.format("id=%d, name=%s, image=%s, price=%.2f" , this.id,
                this.name, this.image,  this.price);
    }
}
