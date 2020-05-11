package com.product.handmade.model;


import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@Entity
@Table(name="places")
@Builder
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor
public class Place {
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
