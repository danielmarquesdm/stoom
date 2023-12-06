package br.com.stoom.store.controller.dto;

import br.com.stoom.store.model.ProductRequestParams;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductRequestParamsDTO {
    private String brand;
    private String category;
    private boolean active;
    private int page;
    private int size;

    public static ProductRequestParams fromRequest(ProductRequestParamsDTO paramsDTO) {
        return ProductRequestParams.builder()
                .brand(paramsDTO.getBrand())
                .category(paramsDTO.getCategory())
                .active(paramsDTO.active)
                .page(paramsDTO.getPage())
                .size(paramsDTO.getSize())
                .build();
    }
}
