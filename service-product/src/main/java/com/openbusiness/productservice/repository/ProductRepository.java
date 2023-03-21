package com.openbusiness.productservice.repository;

import com.openbusiness.productservice.model.Category;
import com.openbusiness.productservice.model.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {

    Iterable<Product> findByCategory(Category category);
}
