package edu.food.model.model;

import lombok.*;

import javax.persistence.*;

@Data
@Builder
@AllArgsConstructor(access = AccessLevel.PACKAGE) @NoArgsConstructor
@Entity
@Table(name = "foods")
public class Food {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 128)
    private String name;

    @Column(length = 128)
    private String image;

    @ManyToOne
    @JoinColumn(name = "foodType_id")
    private FoodType type;

    @Column
    private float price;

    @ManyToOne(fetch = FetchType.LAZY)
    private Place place;
}
