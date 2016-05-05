package ru.dz.recipes.repository

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import ru.dz.recipes.domain.Step
import ru.dz.recipes.domain.StepToProductMapping

/**
 * Created by Alex on 03.05.16.
 */
@Repository
interface StepToProductMappingRepository extends CrudRepository<StepToProductMapping, Long> {
}
