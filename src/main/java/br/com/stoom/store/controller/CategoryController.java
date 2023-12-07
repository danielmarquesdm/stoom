package br.com.stoom.store.controller;

import br.com.stoom.store.business.interfaces.ICategoryBO;
import br.com.stoom.store.controller.dto.CategoryDTO;
import br.com.stoom.store.controller.dto.CategoryRequestDTO;
import br.com.stoom.store.controller.dto.CategoryRequestParamsDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/categories")
public class CategoryController {
    private final ICategoryBO categoryBO;

    @Autowired
    public CategoryController(ICategoryBO categoryBO) {
        this.categoryBO = categoryBO;
    }

    @PostMapping
    public ResponseEntity<CategoryDTO> create(@RequestBody CategoryRequestDTO request) {
        CategoryDTO category = categoryBO.create(CategoryRequestDTO.fromRequest(request));
        return new ResponseEntity<>(category, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<CategoryDTO>> findAll(@RequestParam(value = "category", required = false) String category,
                                                     @RequestParam(value = "active", required = false, defaultValue = "true") boolean active,
                                                     @RequestParam(value = "page", required = false, defaultValue = "0") int page,
                                                     @RequestParam(value = "size", required = false, defaultValue = "10") int size) {
        CategoryRequestParamsDTO paramsDTO = CategoryRequestParamsDTO.builder()
                .category(category)
                .active(active)
                .page(page)
                .size(size)
                .build();
        List<CategoryDTO> categories = categoryBO.findAll(CategoryRequestParamsDTO.fromRequest(paramsDTO));
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable(value = "id") Long id,
                                       @RequestBody CategoryRequestDTO request) {
        categoryBO.update(id, request);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable(value = "id") Long id) {
        categoryBO.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/{id}")
    public ResponseEntity<Void> activate(@PathVariable(value = "id") Long id) {
        categoryBO.activate(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
