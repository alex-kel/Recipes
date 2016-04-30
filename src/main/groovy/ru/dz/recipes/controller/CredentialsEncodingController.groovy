package ru.dz.recipes.controller

import org.apache.commons.codec.binary.Base64
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import ru.dz.recipes.dto.CredentialsEncodingDto

import javax.validation.Valid

/**
 * Created by Alex on 30.04.16.
 */
@Controller
class CredentialsEncodingController {

    @RequestMapping(value = "/encodeCredentials", method = RequestMethod.POST)
    ResponseEntity encodeCredentials(@RequestBody @Valid CredentialsEncodingDto dto) {
        def bytes = (dto.getLogin() + ":" + dto.getPassword()).bytes
        new ResponseEntity(Base64.encodeBase64String(bytes), HttpStatus.OK)
    }
}
