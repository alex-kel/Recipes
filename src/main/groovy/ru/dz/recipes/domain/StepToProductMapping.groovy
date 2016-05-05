package ru.dz.recipes.domain

import org.hibernate.annotations.GenericGenerator

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.OneToOne

/**
 * Created by Alex on 03.05.16.
 */
@Entity
class StepToProductMapping {

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(name = "mapping_id")
    Long id

    @OneToOne
    Step step

    @OneToOne
    Product product

    double amount

    String unit;
}
