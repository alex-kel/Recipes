package ru.dz.recipes.repository

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import ru.dz.recipes.domain.Recipe

/**
 * Created by Alex on 03.05.16.
 */
@Repository
interface RecipeRepository extends CrudRepository<Recipe, Long> {
}
