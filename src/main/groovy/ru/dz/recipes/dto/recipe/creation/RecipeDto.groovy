package ru.dz.recipes.dto.recipe.creation

/**
 * Created by Alex on 03.05.16.
 */
class RecipeDto {

    String name
    String description

    List<ProductDto> products
    List<StepDto> steps
}
