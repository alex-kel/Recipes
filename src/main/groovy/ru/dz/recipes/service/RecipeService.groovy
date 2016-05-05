package ru.dz.recipes.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.ExceptionHandler
import ru.dz.recipes.domain.Product
import ru.dz.recipes.domain.Recipe
import ru.dz.recipes.domain.Step
import ru.dz.recipes.domain.StepToProductMapping
import ru.dz.recipes.dto.recipe.creation.RecipeDto
import ru.dz.recipes.repository.RecipeRepository
import ru.dz.recipes.repository.StepRepository
import ru.dz.recipes.repository.StepToProductMappingRepository

import javax.transaction.Transactional

/**
 * Created by Alex on 05.05.16.
 */
@Service
class RecipeService {

    @Autowired RecipeRepository recipeRepository
    @Autowired StepRepository stepRepository
    @Autowired StepToProductMappingRepository stepToProductMappingRepository

    @Autowired ProductService productService

    Recipe createNewRecipeFromDto(RecipeDto dto) {
        def recipe = new Recipe()
        recipe.name = dto.name
        recipe.steps = new LinkedList<>()
        recipe.products = new LinkedList<>()
        processProducts(dto, recipe)
        processSteps(dto, recipe)
        recipeRepository.save(recipe)
    }

    private void processProducts(RecipeDto dto, Recipe recipe) {
        def products = dto.getProducts()
        products.each { p ->
            def productName = p.name
            def product = productService.getProductByNameOrCreateNew(productName)
            recipe.products.add(product)
        }
    }

    private void processSteps(RecipeDto dto, Recipe recipe) {
        def amountMappingsToSave = new LinkedList<StepToProductMapping>()
        def steps = dto.getSteps()
        steps.each { step ->
            def products  = step.getProducts()
            def stepToStore = new Step()
            stepToStore.description = step.description
            stepToStore.order = step.step
            stepToStore.time = step.time
            stepToStore.products = new LinkedList<>()
            products.each { product ->
                def storedProduct = findProductInRecipeByName(recipe, product.getName())
                stepToStore.products.add(storedProduct)

                def mapping = new StepToProductMapping();
                mapping.amount = product.amount
                mapping.unit = product.unit
                mapping.product = storedProduct
                mapping.step = stepToStore
                amountMappingsToSave.add(mapping)
            }

            stepToStore = stepRepository.save(stepToStore)
            amountMappingsToSave.each { mapping ->
                stepToProductMappingRepository.save(mapping)
            }

            recipe.steps.add(stepToStore)
        }
    }

    private Product findProductInRecipeByName(Recipe recipe, String name) {
        def products = recipe.getProducts()
        for (def p : products) {
            if (p.getName().equals(name)) {
                return p
            }
        }
        throw Exception("Recipe products not filled")
    }
}
