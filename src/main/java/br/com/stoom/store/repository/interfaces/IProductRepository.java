package br.com.stoom.store.repository.interfaces;

import br.com.stoom.store.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface IProductRepository extends JpaRepository<Product, Long>,
        JpaSpecificationExecutor<Product> {
}