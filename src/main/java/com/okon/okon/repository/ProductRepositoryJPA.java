package com.okon.okon.repository;

import com.okon.okon.model.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ProductRepositoryJPA implements ProductRepository {
    @PersistenceContext
    private EntityManager em;

    @Override
    public Product insert(Product product) {
        return em.merge(product);
    }

    @Override
    public Optional<Product> findById(Long id) {
        Product product = em
                .createQuery("SELECT p FROM Product p WHERE p.id = :id", Product.class)
                .setParameter("id", id)
                .getSingleResult();
        return Optional.ofNullable(product);
    }

    @Override
    public List<Product> findAll() {
        return em
                .createQuery("SELECT p FROM Product p", Product.class)
                .getResultList();
    }

    @Override
    public List<Product> findByName(String name) {
        return em
                .createQuery("SELECT p FROM Product p WHERE p.name = :name", Product.class)
                .setParameter("name", name)
                .getResultList();
    }

    @Override
    public void deleteById(Long id) {
        em.createQuery("delete Product p where p.id = :id")
                .setParameter("id", id);
    }
}
