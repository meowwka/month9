package com.product.handmade.model;


import lombok.Data;
import org.springframework.data.annotation.Id;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@Entity
@Table(name="places")
public class Place {
    @javax.persistence.Id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank
    @Size(min = 1, max = 128)
    @Column(length = 128)
    private String name;

    @NotBlank
    @Size(max = 128)
    @Column(length = 128)
    private String address;

    @NotBlank
    @Column(length = 128)
    private String image;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "place")
    @OrderBy("name ASC")
    List<Product> products;

}
