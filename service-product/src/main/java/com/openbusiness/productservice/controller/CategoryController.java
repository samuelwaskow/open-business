package com.openbusiness.productservice.controller;

import com.openbusiness.productservice.dto.CategoryDTO;

import com.openbusiness.productservice.dto.ProductDTO;
import com.openbusiness.productservice.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("category")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody CategoryDTO dto) {
        service.create(dto);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<CategoryDTO> list() {
        return service.list();
    }
}
