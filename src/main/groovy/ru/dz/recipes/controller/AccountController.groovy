package ru.dz.recipes.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController
import ru.dz.recipes.dto.AccountCreationDto
import ru.dz.recipes.dto.AccountUpdateDto
import ru.dz.recipes.service.AccountService
import ru.dz.recipes.service.AuthService

import javax.validation.Valid

/**
 * Created by Alex on 21.03.16.
 */
@RestController
class AccountController {

    @Autowired AccountService accountService

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseBody ResponseEntity register(@RequestBody @Valid AccountCreationDto accountCreationDto) {
        new ResponseEntity(accountService.createNewAccount(accountCreationDto), HttpStatus.CREATED)
    }

    @RequestMapping(value = "/account", method = RequestMethod.PUT)
    @ResponseBody ResponseEntity update(@RequestBody @Valid AccountUpdateDto accountUpdateDto) {
        new ResponseEntity(accountService.updateAccount(accountUpdateDto),HttpStatus.ACCEPTED)
    }

    @RequestMapping(value = "/account", method = RequestMethod.GET)
    @ResponseBody ResponseEntity getCurrentUser() {
        new ResponseEntity(accountService.getCurrentAccount(), HttpStatus.OK)
    }
}
