package com.openbusiness.productservice.dto;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;

import java.io.IOException;
import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

@JsonTest
public class ProductDTOTest {
    @Autowired
    private JacksonTester<ProductDTO> json;

    @Test
    public void productDTOSerializationTest() throws IOException {
        ProductDTO product = new ProductDTO(5L,
                "Pet Cupkake",
                "Pupcakes are a fun way to celebrate your pup!",
                BigDecimal.valueOf(46.00),
                12L,
                "Recipes");

        assertThat(json.write(product)).isStrictlyEqualToJson("product_dto.json");

        assertThat(json.write(product)).hasJsonPathNumberValue("@.id");
        assertThat(json.write(product)).hasJsonPathStringValue("@.name");
        assertThat(json.write(product)).hasJsonPathStringValue("@.description");
        assertThat(json.write(product)).hasJsonPathNumberValue("@.price");
        assertThat(json.write(product)).hasJsonPathNumberValue("@.categoryID");
        assertThat(json.write(product)).hasJsonPathStringValue("@.categoryName");

        assertThat(json.write(product)).extractingJsonPathNumberValue("@.id").isEqualTo(5);
        assertThat(json.write(product)).extractingJsonPathStringValue("@.name").isEqualTo("Pet Cupkake");
        assertThat(json.write(product)).extractingJsonPathStringValue("@.description").isEqualTo("Pupcakes are a fun way to celebrate your pup!");
        assertThat(json.write(product)).extractingJsonPathNumberValue("@.price").isEqualTo(46.00);
        assertThat(json.write(product)).extractingJsonPathNumberValue("@.categoryID").isEqualTo(12);
        assertThat(json.write(product)).extractingJsonPathStringValue("@.categoryName").isEqualTo("Recipes");
    }

}
