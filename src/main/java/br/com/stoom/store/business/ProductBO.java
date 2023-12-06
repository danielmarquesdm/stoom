package br.com.stoom.store.business;

import br.com.stoom.store.business.interfaces.IProductBO;
import br.com.stoom.store.controller.dto.ProductRequestDTO;
import br.com.stoom.store.controller.dto.ProductResponseDTO;
import br.com.stoom.store.model.Product;
import br.com.stoom.store.model.ProductRequestParams;
import br.com.stoom.store.repository.ProductRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductBO implements IProductBO {

    private final ProductRepository productRepository;

    @Autowired
    public ProductBO(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<ProductResponseDTO> findAll(ProductRequestParams params){
        return productRepository.findAllBy(params).stream()
                .map(ProductResponseDTO::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public ProductResponseDTO create(Product product) {
        Product saved = productRepository.save(product);
        return ProductResponseDTO.toResponse(saved);
    }

    @Override
    public void update(Long id, ProductRequestDTO request) {
        Optional<Product> found = productRepository.findById(id);

        found.ifPresent(f -> {
            BeanUtils.copyProperties(request, f);
            productRepository.save(f);
        });
    }

}
