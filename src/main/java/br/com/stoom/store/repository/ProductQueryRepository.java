package br.com.stoom.store.repository;

import br.com.stoom.store.model.Product;
import br.com.stoom.store.model.ProductRequestParams;

import java.util.List;

public interface ProductQueryRepository {
    List<Product> findAllBy(ProductRequestParams params);
}
