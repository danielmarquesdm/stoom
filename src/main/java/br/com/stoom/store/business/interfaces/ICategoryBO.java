package br.com.stoom.store.business.interfaces;

import br.com.stoom.store.controller.dto.CategoryDTO;
import br.com.stoom.store.controller.dto.CategoryRequestDTO;
import br.com.stoom.store.model.Category;
import br.com.stoom.store.model.CategoryRequestParams;

import java.util.List;

public interface ICategoryBO {
    CategoryDTO create(Category category);

    List<CategoryDTO> findAll(CategoryRequestParams categoryRequestParams);

    void update(Long id, CategoryRequestDTO request);

    void delete(Long id);

    void activate(Long id);
}
