package com.openbusiness.productservice;

import com.openbusiness.productservice.dto.ProductDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@Testcontainers
@AutoConfigureMockMvc
class ProductServiceApplicationTests {
    @Container
    private static final MySQLContainer CONTAINER = new MySQLContainer("mysql:8.0");

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

//    @DynamicPropertySource
//    static void setProperties(DynamicPropertyRegistry registry) {
//        registry.add("spring.datasource.url", CONTAINER::getJdbcUrl);
//    }

    @Test
    void shouldCreateProduct() throws Exception {

        final ProductDTO dto = ProductDTO.builder()
                .name("iPhone 11")
                .description("New iPhone from Apple")
                .price(BigDecimal.TEN)
                .build();

        mockMvc.perform(MockMvcRequestBuilders.post("/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsBytes(dto)))
                .andExpect(status().isCreated());
    }

}
