package ru.dz.recipes.domain

import org.hibernate.annotations.GenericGenerator

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

/**
 * Created by Alex on 03.05.16.
 */
@Entity
class Product {

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(name = "product_id")
    Long id;

    String name;

    boolean equals(o) {
        if (this.is(o)) return true
        if (getClass() != o.class) return false

        Product product = (Product) o

        if (id != product.id) return false

        return true
    }

    int hashCode() {
        return (id != null ? id.hashCode() : 0)
    }
}
