package br.com.stoom.store.controller.dto;

import br.com.stoom.store.model.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CategoryRequestDTO {
    private String name;
    private boolean active;

    public static Category fromRequest(CategoryRequestDTO paramsDTO) {
        return Category.builder()
                .name(paramsDTO.getName())
                .active(paramsDTO.isActive())
                .build();
    }
}
