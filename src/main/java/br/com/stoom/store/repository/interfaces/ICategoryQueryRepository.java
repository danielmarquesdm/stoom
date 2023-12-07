package br.com.stoom.store.repository.interfaces;

import br.com.stoom.store.model.Category;
import br.com.stoom.store.model.CategoryRequestParams;

import java.util.List;

public interface ICategoryQueryRepository {
    List<Category> findAllBy(CategoryRequestParams params);
}
