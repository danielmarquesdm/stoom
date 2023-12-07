package br.com.stoom.store.repository.interfaces;

import br.com.stoom.store.model.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface IBrandRepository extends JpaRepository<Brand, Long>, JpaSpecificationExecutor<Brand> {
}
