package br.com.stoom.store.repository;

import br.com.stoom.store.model.Brand;
import br.com.stoom.store.model.BrandRequestParams;
import br.com.stoom.store.repository.interfaces.IBrandQueryRepository;
import br.com.stoom.store.repository.interfaces.IBrandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BrandQueryRepository implements IBrandQueryRepository {

    private final IBrandRepository brandRepository;

    @Autowired
    public BrandQueryRepository(IBrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }

    @Override
    public List<Brand> findAllBy(BrandRequestParams params) {
        Specification<Brand> specification = Specification.where(isActive(params.isActive()));

        if (params.getBrand() != null) {
            specification = hasBrandWithName(params.getBrand());
        }

        List<Brand> all = brandRepository.findAll(specification);
        return all;
    }

    private Specification<Brand> hasBrandWithName(String name) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("name"), "%" + name.toUpperCase() + "%");
    }

    private Specification<Brand> isActive(boolean active) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("active"), active);
    }
}
