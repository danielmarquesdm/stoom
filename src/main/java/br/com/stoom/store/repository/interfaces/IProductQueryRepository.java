package br.com.stoom.store.repository.interfaces;

import br.com.stoom.store.model.Product;
import br.com.stoom.store.model.ProductRequestParams;

import java.util.List;

public interface IProductQueryRepository {
    List<Product> findAllBy(ProductRequestParams params);
}
