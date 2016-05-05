package ru.dz.recipes.repository

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import ru.dz.recipes.domain.Recipe
import ru.dz.recipes.domain.Step

/**
 * Created by Alex on 03.05.16.
 */
@Repository
interface StepRepository extends CrudRepository<Step, Long> {
}
