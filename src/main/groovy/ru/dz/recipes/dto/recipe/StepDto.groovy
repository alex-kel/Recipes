package ru.dz.recipes.dto.recipe

import org.hibernate.validator.constraints.NotEmpty
import ru.dz.recipes.domain.Product
import ru.dz.recipes.domain.Step
import ru.dz.recipes.domain.StepToProductMapping

import javax.validation.constraints.NotNull
import java.util.stream.Collectors

/**
 * Created by Alex on 03.05.16.
 */
class StepDto {

    long id

    int step

    List<ProductDto> products

    @NotNull
    @NotEmpty
    String description

    long time

    StepDto() {}

    StepDto(Step step, List<StepToProductMapping> mapping) {
        this.id = step.id
        this.step = step.order
        this.description = step.description
        this.time = step.time
        def filteredByStepMapping = mapping.stream().filter({m -> m.step.equals(step)}).collect(Collectors.toList())
        this.products = getAllMappedProducts(step, filteredByStepMapping)
    }

    List<ProductDto> getAllMappedProducts(Step step, List<StepToProductMapping> stepToProductMappings) {
        List<ProductDto> result = new LinkedList<>();
        for (def product : step.products) {
            if (isProductMapped(product, stepToProductMappings)) {
                def productDto = new ProductDto()
                def mapping = getMapping(product, stepToProductMappings)
                productDto.id = product.id
                productDto.name = product.name
                productDto.unit = mapping.unit
                productDto.amount = mapping.amount
                result.add(productDto)
            }
        }
        result
    }

    private StepToProductMapping getMapping(Product product, List<StepToProductMapping> mappings) {
        for (def mapping : mappings) {
            if (mapping.product.id.equals(product.id)) {
                return mapping
            }
        }
        null
    }

    private boolean isProductMapped(Product product, List<StepToProductMapping> mappings) {
        for (def m : mappings) {
            if (m.product.id.equals(product.id)) {
                return true
            }
        }
        false
    }
}
