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
public class BrandRequestDTO {
    private String name;
    private Boolean active;

    public static Brand fromRequest(BrandRequestDTO brand) {
        return Brand.builder()
                .name(brand.getName())
                .active(brand.getActive())
                .build();
    }
}
