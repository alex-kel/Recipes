package ru.dz.recipes.dto.recipe.creation

import org.hibernate.validator.constraints.NotEmpty

import javax.validation.constraints.NotNull

/**
 * Created by Alex on 03.05.16.
 */
class StepDto {

    int step

    List<ProductDto> products

    @NotNull
    @NotEmpty
    String description

    long time
}
