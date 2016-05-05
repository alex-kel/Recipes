package ru.dz.recipes.dto

import org.hibernate.validator.constraints.NotEmpty

import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

/**
 * Created by Alex on 03.05.16.
 */
class NewProductDto {

    @NotNull
    @NotEmpty
    @Size(min = 2)
    String name;
}
