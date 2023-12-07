package br.com.stoom.store.controller;

import br.com.stoom.store.business.interfaces.IBrandBO;
import br.com.stoom.store.controller.dto.BrandDTO;
import br.com.stoom.store.controller.dto.BrandRequestDTO;
import br.com.stoom.store.controller.dto.BrandRequestParamsDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/brands")
public class BrandController {

    private final IBrandBO brandBO;

    @Autowired
    public BrandController(IBrandBO brandBO) {
        this.brandBO = brandBO;
    }

    @PostMapping
    public ResponseEntity<BrandDTO> create(@RequestBody BrandRequestDTO request) {
        BrandDTO brand = brandBO.create(BrandRequestDTO.fromRequest(request));
        return new ResponseEntity<>(brand, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<BrandDTO>> findAll(@RequestParam(value = "brand", required = false) String brand,
                                                            @RequestParam(value = "active", required = false, defaultValue = "true") boolean active,
                                                            @RequestParam(value = "page", required = false, defaultValue = "0") int page,
                                                            @RequestParam(value = "size", required = false, defaultValue = "10") int size) {
        BrandRequestParamsDTO paramsDTO = BrandRequestParamsDTO.builder()
                .brand(brand)
                .active(active)
                .page(page)
                .size(size)
                .build();
        List<BrandDTO> brands = brandBO.findAll(BrandRequestParamsDTO.fromRequest(paramsDTO));
        return new ResponseEntity<>(brands, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable(value = "id") Long id,
                                       @RequestBody BrandRequestDTO request) {
        brandBO.update(id, request);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable(value = "id") Long id) {
        brandBO.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/{id}")
    public ResponseEntity<Void> activate(@PathVariable(value = "id") Long id) {
        brandBO.activate(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
