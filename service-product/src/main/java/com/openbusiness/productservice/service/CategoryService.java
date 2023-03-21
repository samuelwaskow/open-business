package com.openbusiness.productservice.service;

import com.openbusiness.productservice.dto.CategoryDTO;
import com.openbusiness.productservice.model.Category;
import com.openbusiness.productservice.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public final class CategoryService {

    private final CategoryRepository repository;

    public void create(CategoryDTO dto) {
        final Category model = Category.builder()
                .name(dto.getName())
                .build();

        repository.save(model);

        log.info("Category {} is saved", model.getId());
    }


    public List<CategoryDTO> list() {
        final List<CategoryDTO> out = new ArrayList<>();
        repository.findAll()
                .forEach(c -> out.add(CategoryDTO.builder()
                        .id(c.getId())
                        .name(c.getName())
                        .build()));
        return out;
    }
}
