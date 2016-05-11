package ru.dz.recipes.dto.recipe

import org.hibernate.validator.constraints.NotEmpty
import ru.dz.recipes.domain.Product
import ru.dz.recipes.domain.ProductToRecipeMapping
import ru.dz.recipes.domain.StepToProductMapping

import javax.validation.constraints.NotNull

/**
 * Created by Alex on 03.05.16.
 */
class ProductDto {

    long id

    @NotNull
    @NotEmpty
    String name

    double amount

    @NotNull
    @NotEmpty
    String unit

    ProductDto() {}

    ProductDto(ProductToRecipeMapping mapping) {
        this.id = mapping.product.id
        this.name = mapping.product.name
        this.amount = mapping.amount
        this.unit = mapping.unit
    }
}
