package br.com.stoom.store.business;

import br.com.stoom.store.business.interfaces.IBrandBO;
import br.com.stoom.store.model.Brand;
import br.com.stoom.store.model.Product;
import br.com.stoom.store.repository.interfaces.IBrandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BrandBO implements IBrandBO {

    private final IBrandRepository brandRepository;

    @Autowired
    public BrandBO(IBrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }

    @Override
    public void delete(Long id) {
        Optional<Brand> found = brandRepository.findById(id);
        found.ifPresent(f -> {
            f.setActive(false);
            brandRepository.save(f);
        });
    }

    @Override
    public void activate(Long id) {
        Optional<Brand> found = brandRepository.findById(id);
        found.ifPresent(f -> {
            f.setActive(true);
            brandRepository.save(f);
        });
    }
}
