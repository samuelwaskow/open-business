package com.openbusiness.productservice.service;

import com.openbusiness.productservice.dto.ProductDTO;
import com.openbusiness.productservice.model.Category;
import com.openbusiness.productservice.model.Product;
import com.openbusiness.productservice.repository.CategoryRepository;
import com.openbusiness.productservice.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {

    private final ProductRepository repository;
    private final CategoryRepository categoryRepository;

    public void create(ProductDTO dto) {
        final Product model = Product.builder()
                .name(dto.getName())
                .description(dto.getDescription())
                .price(dto.getPrice())
                .build();

        repository.save(model);

        log.info("Product {} is saved", model.getId());
    }

    public List<ProductDTO> list(long categoryID) {

        final List<ProductDTO> out = new ArrayList<>();
        final Optional<Category> category = categoryRepository.findById(categoryID);

        if (category.isPresent()) {
            final Iterable<Product> products = repository.findByCategory(category.get());
            products.forEach(p -> out.add(map2DTO(p)));
        } else {
            final Iterable<Product> products = repository.findAll();
            products.forEach(p -> out.add(map2DTO(p)));
        }

        return out;
    }


    public void update(ProductDTO dto) {

        final Product persisted = getPersistedProduct(dto.getId());

        final Product model = Product.builder()
                .id(persisted.getId())
                .name(dto.getName())
                .description(dto.getDescription())
                .price(dto.getPrice())
                .build();

        repository.save(model);

        log.info("Product {} is updated", model.getId());
    }


    public void delete(long id) {
        final Product persisted = getPersistedProduct(id);
        repository.delete(persisted);
        log.info("Product {} is deleted", id);
    }

    private Product getPersistedProduct(final long id) {
        final Optional<Product> persisted = repository.findById(id);
        if (!persisted.isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Existing product not found");
        }
        return persisted.get();
    }

    private static ProductDTO map2DTO(Product p) {
        return ProductDTO.builder()
                .id(p.getId())
                .name(p.getName())
                .description(p.getDescription())
                .price(p.getPrice())
                .categoryID(p.getCategory() == null ? 0 : p.getCategory().getId())
                .categoryName(p.getCategory() == null ? "" : p.getCategory().getName())
                .build();
    }

}
