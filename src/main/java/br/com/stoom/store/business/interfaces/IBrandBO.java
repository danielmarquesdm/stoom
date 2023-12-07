package br.com.stoom.store.business.interfaces;

import br.com.stoom.store.controller.dto.BrandDTO;
import br.com.stoom.store.controller.dto.BrandRequestDTO;
import br.com.stoom.store.model.Brand;
import br.com.stoom.store.model.BrandRequestParams;

import java.util.List;

public interface IBrandBO {
    void delete(Long id);

    void activate(Long id);

    BrandDTO create(Brand brand);

    List<BrandDTO> findAll(BrandRequestParams brandRequestParams);

    void update(Long id, BrandRequestDTO request);
}
