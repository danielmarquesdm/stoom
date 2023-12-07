package br.com.stoom.store.repository;

import br.com.stoom.store.model.Category;
import br.com.stoom.store.model.CategoryRequestParams;
import br.com.stoom.store.repository.interfaces.ICategoryQueryRepository;
import br.com.stoom.store.repository.interfaces.ICategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CategoryQueryRepository implements ICategoryQueryRepository {

    private final ICategoryRepository categoryRepository;

    @Autowired
    public CategoryQueryRepository(ICategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Category> findAllBy(CategoryRequestParams params) {
        Specification<Category> specification = Specification.where(isActive(params.isActive()));

        if (params.getCategory() != null) {
            specification = hasCategoryWithName(params.getCategory());
        }

        return categoryRepository.findAll(specification);
    }

    private Specification<Category> hasCategoryWithName(String name) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("name"), "%" + name.toUpperCase() + "%");
    }

    private Specification<Category> isActive(boolean active) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("active"), active);
    }
}
