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
public class ProductRequestDTO {
    private String sku;
    private CategoryDTO category;
    private BrandDTO brand;
    private BigDecimal price;
    private boolean active;

    public static Product fromRequest(ProductRequestDTO request) {
        return Product.builder()
                .sku(request.getSku())
                .category(CategoryDTO.fromRequest(request.getCategory()))
                .brand(BrandDTO.fromRequest(request.getBrand()))
                .price(request.getPrice())
                .active(request.isActive())
                .build();
    }
}
