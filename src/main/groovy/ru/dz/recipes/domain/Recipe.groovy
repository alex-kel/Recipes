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

    @ManyToMany
    @JoinTable
    List<Product> products

    @OneToMany
    List<Step> steps

    @OneToMany
    List<Account> staredBy;
}
