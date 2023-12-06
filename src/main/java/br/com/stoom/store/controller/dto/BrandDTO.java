package br.com.stoom.store.controller.dto;

import br.com.stoom.store.model.Brand;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BrandDTO {
    private Long id;
    private String name;

    public static Brand fromRequest(BrandDTO brand) {
        return Brand.builder()
                .id(brand.getId())
                .name(brand.getName())
                .build();
    }

    public static BrandDTO toResponse(Brand brand) {
        return BrandDTO.builder()
                .id(brand.getId())
                .name(brand.getName())
                .build();
    }
}
