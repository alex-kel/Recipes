package ru.dz.recipes.domain

import org.hibernate.annotations.GenericGenerator

import javax.persistence.CascadeType
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
class Recipe {

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(name = "recipe_id")
    Long id

    String name

    String description

    String imageUrl

    @ManyToMany
    @JoinTable
    List<Product> products

    @OneToMany
    List<Step> steps

    @OneToMany
    List<Account> staredBy;

    boolean equals(o) {
        if (this.is(o)) return true
        if (getClass() != o.class) return false

        Recipe recipe = (Recipe) o

        if (id != recipe.id) return false

        return true
    }

    int hashCode() {
        return (id != null ? id.hashCode() : 0)
    }
}
