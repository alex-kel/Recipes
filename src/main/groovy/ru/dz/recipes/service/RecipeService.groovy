package ru.dz.recipes.service

import org.springframework.beans.factory.annotation.Autowire
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service
import ru.dz.recipes.domain.Product
import ru.dz.recipes.domain.ProductToRecipeMapping
import ru.dz.recipes.domain.Recipe
import ru.dz.recipes.domain.Step
import ru.dz.recipes.domain.StepToProductMapping
import ru.dz.recipes.dto.recipe.RecipeDto
import ru.dz.recipes.exceptions.RecipeNotFoundException
import ru.dz.recipes.repository.ProductToRecipeMappingRepository
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
    @Autowired ProductToRecipeMappingRepository productToRecipeMappingRepository

    @Autowired ProductService productService

    @Transactional
    Recipe createNewRecipeFromDto(RecipeDto dto) {
        def recipe = new Recipe()
        recipe.name = dto.name
        recipe.description = dto.description
        recipe.imageUrl = dto.imageUrl
        recipe.steps = new LinkedList<>()
        recipe.products = new LinkedList<>()
        def productsToRecipeMapping = processProducts(dto, recipe)
        processSteps(dto, recipe)
        recipe = recipeRepository.save(recipe)
        productsToRecipeMapping.each { mapping ->
            productToRecipeMappingRepository.save(mapping)
        }
        recipe
    }

    private List<ProductToRecipeMapping> processProducts(RecipeDto dto, Recipe recipe) {
        def mappings = new LinkedList<ProductToRecipeMapping>()
        def products = dto.getProducts()
        products.each { p ->
            def mapping = new ProductToRecipeMapping()
            def productName = p.name
            def product = productService.getProductByNameOrCreateNew(productName)
            mapping.recipe = recipe
            mapping.product = product
            mapping.amount = p.amount
            mapping.unit = p.unit
            mappings.add(mapping)
        }
        mappings
    }

    private void processSteps(RecipeDto dto, Recipe recipe) {
        def amountMappingsToSave = new LinkedList<StepToProductMapping>()
        def steps = dto.getSteps()
        steps.each { step ->
            def products  = step.products
            def stepToStore = new Step()
            stepToStore.description = step.description
            stepToStore.order = step.step
            stepToStore.time = step.time
            stepToStore.products = new LinkedList<>()
            products.each { product ->
                def storedProduct = productService.getProductByNameOrCreateNew(product.name)
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
        throw new Exception("Recipe products not filled")
    }

    RecipeDto getRecipeDtoById(long id) {
        def recipe = recipeRepository.findOne(id)
        if (recipe == null) {
            throw new RecipeNotFoundException();
        }
        List<StepToProductMapping> stepMapping = new LinkedList<>();
        recipe.steps.each { step ->
            stepMapping.addAll(stepToProductMappingRepository.findByStep(step))
        }

        List<ProductToRecipeMapping> productMapping = productToRecipeMappingRepository.findByRecipe(recipe)

        new RecipeDto(recipe, productMapping, stepMapping)
    }

    List<RecipeDto> getPagedRecipes(int page, int size) {
        def result = new LinkedList<RecipeDto>()
        Page<Recipe> retrievedPage = recipeRepository.findAll(new PageRequest(page, size))
        retrievedPage.content.each { recipe ->
            List<StepToProductMapping> stepMapping = new LinkedList<>();
            recipe.steps.each { step ->
                stepMapping.addAll(stepToProductMappingRepository.findByStep(step))
            }

            List<ProductToRecipeMapping> productMapping = productToRecipeMappingRepository.findByRecipe(recipe)

            result.add(new RecipeDto(recipe, productMapping, stepMapping))
        }
        result
    }
}
