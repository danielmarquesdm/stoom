package br.com.stoom.store.repository;

import br.com.stoom.store.model.Brand;
import br.com.stoom.store.model.BrandRequestParams;
import br.com.stoom.store.repository.interfaces.IBrandQueryRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BrandQueryRepository implements IBrandQueryRepository {
    @Override
    public List<Brand> findAllBy(BrandRequestParams params) {
        return null;
    }
}
