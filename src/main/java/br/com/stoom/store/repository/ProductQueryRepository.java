package br.com.stoom.store.repository;

import br.com.stoom.store.model.Brand;
import br.com.stoom.store.model.Category;
import br.com.stoom.store.model.Product;
import br.com.stoom.store.model.ProductRequestParams;
import br.com.stoom.store.repository.interfaces.IProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.Join;
import java.util.List;

@Repository
public class ProductQueryRepository {

    private final br.com.stoom.store.repository.interfaces.IProductRepository IProductRepository;

    @Autowired
    public ProductQueryRepository(IProductRepository IProductRepository) {
        this.IProductRepository = IProductRepository;
    }

    public List<Product> findAllBy(ProductRequestParams params) {
        Specification<Product> specification = Specification.where(isActive(params.isActive()));

        if (params.getBrand() != null) {
            specification = hasProductWithBrandName(params.getBrand());
        }

        if (params.getCategory() != null) {
            specification = specification.and(hasProductWithCategoryName(params.getCategory()));
        }

        return IProductRepository.findAll(specification);
    }

    private Specification<Product> hasProductWithBrandName(String brandName) {
        return (root, query, criteriaBuilder) -> {
            Join<Brand, Product> productBrand = root.join("brand");
            return criteriaBuilder.like(productBrand.get("name"), "%" + brandName.toUpperCase() + "%");
        };
    }

    private Specification<Product> hasProductWithCategoryName(String categoryName) {
        return (root, query, criteriaBuilder) -> {
            Join<Category, Product> productCategory = root.join("category");
            return criteriaBuilder.like(productCategory.get("name"), "%" + categoryName.toUpperCase() + "%");
        };
    }

    private Specification<Product> isActive(boolean active) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("active"), active);
    }
}
