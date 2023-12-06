package br.com.stoom.store.controller;

import br.com.stoom.store.business.ProductBO;
import br.com.stoom.store.controller.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/products")
public class ProductController {

    private final ProductBO productBO;

    @Autowired
    public ProductController(ProductBO productBO) {
        this.productBO = productBO;
    }

    @PostMapping
    public ResponseEntity<ProductResponseDTO> create(@RequestBody ProductRequestDTO productRequestDTO) {
        ProductResponseDTO product = productBO.create(ProductRequestDTO.fromRequest(productRequestDTO));
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<ProductResponseDTO>> findAll(@RequestParam(value = "brand", required = false) String brand,
                                                 @RequestParam(value = "category", required = false) String category,
                                                 @RequestParam(value = "page", required = false, defaultValue = "0") int page,
                                                 @RequestParam(value = "size", required = false, defaultValue = "10") int size) {
        ProductRequestParamsDTO paramsDTO = ProductRequestParamsDTO.builder()
                .brand(brand)
                .category(category)
                .page(page)
                .size(size)
                .build();
        List<ProductResponseDTO> products = productBO.findAll(ProductRequestParamsDTO.fromRequest(paramsDTO));
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductResponseDTO> update(@PathVariable(value = "id") Long id,
                                                     @RequestBody ProductRequestDTO request) {
        productBO.update(id, request);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
