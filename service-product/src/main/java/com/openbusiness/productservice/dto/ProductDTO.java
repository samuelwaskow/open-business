package com.openbusiness.productservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {

    private long id;
    private String name;
    private String description;
    private BigDecimal price;
    private long categoryID;
    private String categoryName;
}
