package ru.dz.recipes.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import ru.dz.recipes.domain.Product
import ru.dz.recipes.dto.NewProductDto
import ru.dz.recipes.exceptions.ProductNameAlreadyExists
import ru.dz.recipes.repository.ProductRepository

/**
 * Created by Alex on 03.05.16.
 */
@Service
class ProductService {

    @Autowired ProductRepository productRepository

    Product createNewProduct(NewProductDto dto) {
        def product = productRepository.findByName(dto.name)
        if (product != null) {
            throw new ProductNameAlreadyExists();
        }

        createNewProductByName(dto.getName())
    }

    Product createNewProductByName(String name) {
        def product = new Product()
        product.setName(name)
        productRepository.save(product)
    }

    boolean alreadyExists(String name) {
        return productRepository.findByName(name) != null
    }

    Product getProductByName(String name) {
        return productRepository.findByName(name)
    }

    Product getProductByNameOrCreateNew(String name) {
        if (alreadyExists(name)) {
            getProductByName(name)
        } else {
            createNewProductByName(name)
        }
    }
}
