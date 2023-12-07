package br.com.stoom.store.repository.interfaces;

import br.com.stoom.store.model.Brand;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IBrandRepository extends JpaRepository<Brand, Long> {
}
