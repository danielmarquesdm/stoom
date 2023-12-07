package br.com.stoom.store.business;

import br.com.stoom.store.business.interfaces.IProductBO;
import br.com.stoom.store.controller.dto.ProductRequestDTO;
import br.com.stoom.store.controller.dto.ProductResponseDTO;
import br.com.stoom.store.model.Product;
import br.com.stoom.store.model.ProductRequestParams;
import br.com.stoom.store.repository.ProductQueryRepository;
import br.com.stoom.store.repository.interfaces.IProductRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductBO implements IProductBO {

    private final IProductRepository productRepository;
    private final ProductQueryRepository productQueryRepository;

    @Autowired
    public ProductBO(IProductRepository productRepository, ProductQueryRepository productQueryRepository) {
        this.productRepository = productRepository;
        this.productQueryRepository = productQueryRepository;
    }

    @Override
    public List<ProductResponseDTO> findAll(ProductRequestParams params) {
        return productQueryRepository.findAllBy(params).stream()
                .map(ProductResponseDTO::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public ProductResponseDTO create(Product product) {
        String categoryName = product.getCategory().getName();
        String brandName = product.getBrand().getName();
        product.getCategory().setName(categoryName.toUpperCase());
        product.getBrand().setName(brandName.toUpperCase());
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

    @Override
    public void delete(Long id) {
        Optional<Product> found = productRepository.findById(id);
        found.ifPresent(f -> {
            f.setActive(false);
            productRepository.save(f);
        });
    }

    @Override
    public void activate(Long id) {
        Optional<Product> found = productRepository.findById(id);
        found.ifPresent(f -> {
            f.setActive(true);
            productRepository.save(f);
        });
    }
}
