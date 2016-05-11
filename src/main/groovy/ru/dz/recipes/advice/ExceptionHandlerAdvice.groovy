package ru.dz.recipes.advice

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import ru.dz.recipes.exceptions.LoginAlreadyInUseException
import ru.dz.recipes.exceptions.ProductNameAlreadyExists
import ru.dz.recipes.exceptions.RecipeNotFoundException

/**
 * Created by Alex on 30.04.16.
 */
@ControllerAdvice
class ExceptionHandlerAdvice {

    @ResponseStatus(code = HttpStatus.CONFLICT, reason = "Login already in use")
    @ExceptionHandler(LoginAlreadyInUseException.class)
    void handleLoginAlreadyInUse() {}

    @ResponseStatus(code = HttpStatus.CONFLICT, reason = "Product with specified name already exists")
    @ExceptionHandler(ProductNameAlreadyExists.class)
    void handleProductNameAlreadyInUse() {}

    @ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Recipe not found")
    @ExceptionHandler(RecipeNotFoundException.class)
    void handleRecipeNotExists() {}
}
