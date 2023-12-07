package br.com.stoom.store.business;

import br.com.stoom.store.business.interfaces.IProductBO;
import br.com.stoom.store.controller.dto.ProductRequestDTO;
import br.com.stoom.store.controller.dto.ProductResponseDTO;
import br.com.stoom.store.model.Product;
import br.com.stoom.store.model.ProductRequestParams;
import br.com.stoom.store.repository.interfaces.IProductRepository;
import br.com.stoom.store.repository.ProductQueryRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductBO implements IProductBO {

    private final IProductRepository IProductRepository;
    private final ProductQueryRepository productQuery;

    @Autowired
    public ProductBO(IProductRepository IProductRepository, ProductQueryRepository productQuery) {
        this.IProductRepository = IProductRepository;
        this.productQuery = productQuery;
    }

    @Override
    public List<ProductResponseDTO> findAll(ProductRequestParams params) {
        return productQuery.findAllBy(params).stream()
                .map(ProductResponseDTO::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public ProductResponseDTO create(Product product) {
        String categoryName = product.getCategory().getName();
        String brandName = product.getBrand().getName();
        product.getCategory().setName(categoryName.toUpperCase());
        product.getBrand().setName(brandName.toUpperCase());
        Product saved = IProductRepository.save(product);
        return ProductResponseDTO.toResponse(saved);
    }

    @Override
    public void update(Long id, ProductRequestDTO request) {
        Optional<Product> found = IProductRepository.findById(id);

        found.ifPresent(f -> {
            BeanUtils.copyProperties(request, f);
            IProductRepository.save(f);
        });
    }

    @Override
    public void delete(Long id) {
        Optional<Product> found = IProductRepository.findById(id);
        found.ifPresent(f -> {
            f.setActive(false);
            IProductRepository.save(f);
        });
    }

    @Override
    public void activate(Long id) {
        Optional<Product> found = IProductRepository.findById(id);
        found.ifPresent(f -> {
            f.setActive(true);
            IProductRepository.save(f);
        });
    }
}
