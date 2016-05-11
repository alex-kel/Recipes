package ru.dz.recipes.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController
import ru.dz.recipes.dto.recipe.RecipeDto
import ru.dz.recipes.service.ProductService
import ru.dz.recipes.service.RecipeService

import javax.validation.Valid

/**
 * Created by Alex on 02.05.16.
 */
@RestController
class MobileApiProviderController {

    @Autowired ProductService productService
    @Autowired RecipeService recipeService

    @RequestMapping(value = "/recipe", method = RequestMethod.POST)
    @ResponseBody ResponseEntity createRecipe(@RequestBody @Valid RecipeDto dto) {
        new ResponseEntity(recipeService.createNewRecipeFromDto(dto), HttpStatus.OK)
    }


    @RequestMapping(value = "/recipe/{id}", method = RequestMethod.GET)
    @ResponseBody ResponseEntity getRecipe(@PathVariable("id") long id) {
        new ResponseEntity(recipeService.getRecipeDtoById(id), HttpStatus.OK)
    }

    @RequestMapping(value = "/recipes", params = ["page", "size"], method = RequestMethod.GET)
    @ResponseBody ResponseEntity getAllRecipesPaged(@RequestParam( "page" ) int page, @RequestParam( "size" ) int size) {
        new ResponseEntity(recipeService.getPagedRecipes(page, size), HttpStatus.OK)
    }
}
