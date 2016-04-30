package ru.dz.receipts.controller

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController

/**
 * Created by Alex on 21.03.16.
 */
@RestController
class UserController {

    @RequestMapping("users/{name}")
    @ResponseBody ResponseEntity user(@PathVariable String name) {
        return new ResponseEntity(name, HttpStatus.OK)
    }
}
