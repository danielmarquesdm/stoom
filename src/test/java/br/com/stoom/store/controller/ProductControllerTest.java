package br.com.stoom.store.controller;

import br.com.stoom.store.business.ProductBO;
import br.com.stoom.store.controller.dto.BrandDTO;
import br.com.stoom.store.controller.dto.CategoryDTO;
import br.com.stoom.store.controller.dto.ProductRequestDTO;
import br.com.stoom.store.controller.dto.ProductResponseDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
class ProductControllerTest {
    @InjectMocks
    private ProductController productController;

    @Mock
    private ProductBO productBO;

    @Test
    void shouldCreate() {
        ProductRequestDTO request = ProductRequestDTO.builder()
                .sku("123")
                .price(BigDecimal.ZERO)
                .active(true)
                .brand(BrandDTO.builder().id(1L).name("NIKE").active(true).build())
                .category(CategoryDTO.builder().id(1L).name("Calcados").active(true).build())
                .build();
        ProductResponseDTO expected = ProductResponseDTO.builder()
                .sku("123")
                .price(BigDecimal.ZERO)
                .active(true)
                .brand(BrandDTO.builder().id(1L).name("NIKE").active(true).build())
                .category(CategoryDTO.builder().id(1L).name("Calcados").active(true).build())
                .build();
        when(productBO.create(any())).thenReturn(expected);

        ResponseEntity<ProductResponseDTO> actual = productController.create(request);

        Assertions.assertEquals(expected, actual.getBody());
    }

}
