package br.com.stoom.store.business.interfaces;

import br.com.stoom.store.controller.dto.ProductRequestDTO;
import br.com.stoom.store.controller.dto.ProductResponseDTO;
import br.com.stoom.store.model.Product;
import br.com.stoom.store.model.ProductRequestParams;

import java.util.List;

public interface IProductBO {

    List<ProductResponseDTO> findAll(ProductRequestParams params);

    ProductResponseDTO create(Product product);

    void update(Long id, ProductRequestDTO request);

    void delete(Long id);
}
