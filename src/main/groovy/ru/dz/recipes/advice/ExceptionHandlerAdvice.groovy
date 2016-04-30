package ru.dz.recipes.advice

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import ru.dz.recipes.exceptions.LoginAlreadyInUseException

/**
 * Created by Alex on 30.04.16.
 */
@ControllerAdvice
class ExceptionHandlerAdvice {

    @ResponseStatus(code = HttpStatus.FORBIDDEN, reason = "Login already in use")
    @ExceptionHandler(LoginAlreadyInUseException.class)
    void handleLoginAlreadyInUse() {

    }
}