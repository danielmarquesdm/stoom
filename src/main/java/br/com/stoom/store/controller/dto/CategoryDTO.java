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
public class CategoryDTO {
    private Long id;
    private String name;
    private Boolean active;

    public static CategoryDTO toResponse(Category category) {
        return CategoryDTO.builder()
                .id(category.getId())
                .name(category.getName())
                .active(category.getActive())
                .build();
    }

    public static Category fromRequest(CategoryDTO category) {
        return Category.builder()
                .id(category.getId())
                .name(category.getName())
                .active(category.getActive())
                .build();
    }
}
