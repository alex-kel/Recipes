package ru.dz.recipes.domain

import org.hibernate.annotations.GenericGenerator

import javax.persistence.*

/**
 * Created by Alex on 03.05.16.
 */
@Entity
class ProductToRecipeMapping {

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(name = "mapping_id")
    Long id

    @OneToOne
    Product product

    @OneToOne
    Recipe recipe

    double amount

    String unit;
}
