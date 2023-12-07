package br.com.stoom.store.controller.dto;

import br.com.stoom.store.model.BrandRequestParams;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BrandRequestParamsDTO {
    private String brand;
    private boolean active;
    private int page;
    private int size;

    public static BrandRequestParams fromRequest(BrandRequestParamsDTO paramsDTO) {
        return BrandRequestParams.builder()
                .brand(paramsDTO.getBrand())
                .active(paramsDTO.isActive())
                .page(paramsDTO.getPage())
                .size(paramsDTO.getSize())
                .build();
    }
}
