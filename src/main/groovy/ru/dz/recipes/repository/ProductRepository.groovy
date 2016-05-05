package ru.dz.recipes.repository

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import ru.dz.recipes.domain.Product

/**
 * Created by Alex on 22.03.16.
 */
@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {

    public Product findByName(String name);
}
