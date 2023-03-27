package com.openbusiness.productservice.controller;

import com.openbusiness.productservice.dto.ProductDTO;
import com.openbusiness.productservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody ProductDTO dto) {
        service.create(dto);
    }

    @GetMapping("/{categoryID}")
    @ResponseStatus(HttpStatus.OK)
    public List<ProductDTO> list(@PathVariable("categoryID") long categoryID) {
        return service.list(categoryID);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public void update(@RequestBody ProductDTO dto) {
        service.update(dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable("id") long id) {
        service.delete(id);
    }

}
