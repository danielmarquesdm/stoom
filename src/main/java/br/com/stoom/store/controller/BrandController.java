package br.com.stoom.store.controller;

import br.com.stoom.store.business.interfaces.IBrandBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/brands")
public class BrandController {

    private final IBrandBO brandBO;

    @Autowired
    public BrandController(IBrandBO brandBO) {
        this.brandBO = brandBO;
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
