package br.com.stoom.store.repository.impl;

import br.com.stoom.store.model.Product;
import br.com.stoom.store.model.ProductRequestParams;
import br.com.stoom.store.repository.ProductQueryRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ProductQueryRepositoryImpl implements ProductQueryRepository {
    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<Product> findAllBy(ProductRequestParams params) {
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<Product> criteriaQuery = builder.createQuery(Product.class);

        Root<Product> root = criteriaQuery.from(Product.class);

        List<Predicate> predicates = new ArrayList<>();

        if (params.getBrand() != null) {
            Predicate brandPredicate = builder.like(root.get("brand"), "%" + params.getBrand() + "%");
            predicates.add(brandPredicate);
        }

        if (params.getCategory() != null) {
            Predicate categoryPredicate = builder.equal(root.get("category"), params.getCategory());
            predicates.add(categoryPredicate);
        }

        criteriaQuery.where(predicates.toArray(new Predicate[0]));

        TypedQuery<Product> query = manager.createQuery(criteriaQuery);

        return query.getResultList();
    }
}
