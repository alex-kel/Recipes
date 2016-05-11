package ru.dz.recipes.dto.recipe

import org.hibernate.validator.constraints.NotEmpty
import ru.dz.recipes.domain.ProductToRecipeMapping
import ru.dz.recipes.domain.Recipe
import ru.dz.recipes.domain.StepToProductMapping

import javax.validation.constraints.NotNull

/**
 * Created by Alex on 03.05.16.
 */
class RecipeDto {
    long id

    @NotNull
    @NotEmpty
    String name

    @NotNull
    @NotEmpty
    String description

    @NotNull
    @NotEmpty
    String imageUrl

    List<ProductDto> products
    List<StepDto> steps

    RecipeDto() {}

    RecipeDto(Recipe recipe, List<ProductToRecipeMapping> productMapping, List<StepToProductMapping> stepMapping) {
        this.id = recipe.id
        this.name = recipe.name
        this.description = recipe.description
        this.imageUrl = recipe.imageUrl

        this.products = new LinkedList<>()
        productMapping.each {mapping ->
            this.products.add(new ProductDto(mapping))
        }

        this.steps = new LinkedList<>()
        recipe.steps.each { step ->
            this.steps.add(new StepDto(step, stepMapping))
        }
    }
}
