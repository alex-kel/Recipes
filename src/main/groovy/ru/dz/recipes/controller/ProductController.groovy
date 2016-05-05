package ru.dz.recipes.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController
import ru.dz.recipes.dto.NewProductDto
import ru.dz.recipes.service.ProductService

import javax.validation.Valid

/**
 * Created by Alex on 02.05.16.
 */
@RestController
class ProductController {

    @Autowired ProductService productService

    @RequestMapping(value = "/product", method = RequestMethod.POST)
    @ResponseBody ResponseEntity createNewProduct(@RequestBody @Valid NewProductDto dto) {
        productService.createNewProduct(dto)
    }


}
