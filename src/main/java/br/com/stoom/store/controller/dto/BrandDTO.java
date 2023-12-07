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
    private Boolean active;

    public static Brand fromRequest(BrandDTO brand) {
        return Brand.builder()
                .name(brand.getName())
                .active(brand.getActive())
                .build();
    }

    public static BrandDTO toResponse(Brand brand) {
        return BrandDTO.builder()
                .id(brand.getId())
                .name(brand.getName())
                .build();
    }
}
