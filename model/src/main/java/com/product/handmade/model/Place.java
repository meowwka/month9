package com.product.handmade.model;


import lombok.Data;
import org.springframework.data.annotation.Id;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name="places")
public class Place {
    @javax.persistence.Id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 128)
    private String name;

    @Column(length = 128)
    private String address;
    @Column(length = 128)
    private String image;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "place")
    @OrderBy("name ASC")
    List<Product> products;

}
