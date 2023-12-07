package br.com.stoom.store.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CategoryRequestParams {
    private String category;
    private boolean active;
    private int page;
    private int size;
}
