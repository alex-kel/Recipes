package ru.dz.recipes.dto.recipe.creation

import org.hibernate.validator.constraints.NotEmpty

import javax.validation.constraints.NotNull

/**
 * Created by Alex on 03.05.16.
 */
class RecipeDto {
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
}
