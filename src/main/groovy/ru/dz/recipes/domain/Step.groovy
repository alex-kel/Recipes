package ru.dz.recipes.domain

import org.hibernate.annotations.GenericGenerator

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.JoinTable
import javax.persistence.ManyToMany
import javax.persistence.OneToMany

/**
 * Created by Alex on 03.05.16.
 */
@Entity
class Step {

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(name = "step_id")
    Long id

    @ManyToMany
    @JoinTable
    List<Product> products;

    String description

    Long time

    @Column(name = "step_order")
    int order

    boolean equals(o) {
        if (this.is(o)) return true
        if (getClass() != o.class) return false

        Step step = (Step) o

        if (id != step.id) return false

        return true
    }

    int hashCode() {
        return id.hashCode()
    }
}
