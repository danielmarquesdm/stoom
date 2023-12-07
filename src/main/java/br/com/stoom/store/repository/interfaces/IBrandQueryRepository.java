package br.com.stoom.store.repository.interfaces;

import br.com.stoom.store.model.Brand;
import br.com.stoom.store.model.BrandRequestParams;

import java.util.List;

public interface IBrandQueryRepository {
    List<Brand> findAllBy(BrandRequestParams params);
}
