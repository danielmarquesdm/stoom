package br.com.stoom.store.business;

import br.com.stoom.store.business.interfaces.IBrandBO;
import br.com.stoom.store.controller.dto.BrandDTO;
import br.com.stoom.store.controller.dto.BrandRequestDTO;
import br.com.stoom.store.model.Brand;
import br.com.stoom.store.model.BrandRequestParams;
import br.com.stoom.store.repository.interfaces.IBrandQueryRepository;
import br.com.stoom.store.repository.interfaces.IBrandRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BrandBO implements IBrandBO {

    private final IBrandRepository brandRepository;
    private final IBrandQueryRepository brandQueryRepository;

    @Autowired
    public BrandBO(IBrandRepository brandRepository, IBrandQueryRepository brandQueryRepository) {
        this.brandRepository = brandRepository;
        this.brandQueryRepository = brandQueryRepository;
    }

    @Override
    public BrandDTO create(Brand brand) {
        Brand changed = changeStringToUpperCase(brand);
        Brand saved = brandRepository.save(changed);
        return BrandDTO.toResponse(saved);
    }

    private static Brand changeStringToUpperCase(Brand brand) {
        String name = brand.getName();
        return brand.toBuilder().name(name.toUpperCase()).build();
    }

    @Override
    public List<BrandDTO> findAll(BrandRequestParams params) {
        return brandQueryRepository.findAllBy(params).stream()
                .map(BrandDTO::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public void update(Long id, BrandRequestDTO request) {
        Optional<Brand> found = brandRepository.findById(id);

        found.ifPresent(f -> {
            BeanUtils.copyProperties(request, f);
            Brand changed = changeStringToUpperCase(f);
            brandRepository.save(changed);
        });
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
