package br.com.stoom.store.controller.dto;

import br.com.stoom.store.model.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductResponseDTO {
    private Long id;
    private String sku;
    private CategoryDTO category;
    private BrandDTO brand;
    private BigDecimal price;
    private boolean active;

    public static ProductResponseDTO toResponse(Product product) {
        return ProductResponseDTO.builder()
                .id(product.getId())
                .sku(product.getSku())
                .category(CategoryDTO.toResponse(product.getCategory()))
                .brand(BrandDTO.toResponse(product.getBrand()))
                .price(product.getPrice())
                .active(product.isActive())
                .build();
    }
}
