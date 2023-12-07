package br.com.stoom.store.controller.dto;

import br.com.stoom.store.model.CategoryRequestParams;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CategoryRequestParamsDTO {
    private String category;
    private boolean active;
    private int page;
    private int size;

    public static CategoryRequestParams fromRequest(CategoryRequestParamsDTO paramsDTO) {
        return CategoryRequestParams.builder()
                .category(paramsDTO.getCategory())
                .active(paramsDTO.isActive())
                .page(paramsDTO.getPage())
                .size(paramsDTO.getSize())
                .build();
    }
}
