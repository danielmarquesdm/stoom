package br.com.stoom.store.business;

import br.com.stoom.store.business.interfaces.ICategoryBO;
import br.com.stoom.store.controller.dto.CategoryDTO;
import br.com.stoom.store.controller.dto.CategoryRequestDTO;
import br.com.stoom.store.model.Category;
import br.com.stoom.store.model.CategoryRequestParams;
import br.com.stoom.store.repository.interfaces.ICategoryQueryRepository;
import br.com.stoom.store.repository.interfaces.ICategoryRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryBO implements ICategoryBO {

    private final ICategoryRepository categoryRepository;
    private final ICategoryQueryRepository categoryQueryRepository;

    @Autowired
    public CategoryBO(ICategoryRepository categoryRepository, ICategoryQueryRepository categoryQueryRepository) {
        this.categoryRepository = categoryRepository;
        this.categoryQueryRepository = categoryQueryRepository;
    }

    @Override
    public CategoryDTO create(Category category) {
        Category saved = categoryRepository.save(category.toBuilder()
                .name(category.getName().toUpperCase())
                .build());
        return CategoryDTO.toResponse(saved);
    }

    @Override
    public List<CategoryDTO> findAll(CategoryRequestParams params) {
        return categoryQueryRepository.findAllBy(params).stream()
                .map(CategoryDTO::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public void update(Long id, CategoryRequestDTO request) {
        Optional<Category> found = categoryRepository.findById(id);

        found.ifPresent(f -> {
            BeanUtils.copyProperties(request, f);
            categoryRepository.save(f.toBuilder()
                    .name(f.getName().toUpperCase())
                    .build());
        });
    }

    @Override
    public void delete(Long id) {
        Optional<Category> found = categoryRepository.findById(id);
        found.ifPresent(f -> {
            f.setActive(false);
            categoryRepository.save(f);
        });
    }

    @Override
    public void activate(Long id) {
        Optional<Category> found = categoryRepository.findById(id);
        found.ifPresent(f -> {
            f.setActive(true);
            categoryRepository.save(f);
        });
    }
}
