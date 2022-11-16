package com.okon.okon.repository;

import com.okon.okon.model.Product;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ProductRepositoryJPA implements ProductRepository {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Product insert(Product product) {
        try (Session s = sessionFactory.getCurrentSession()) {
            s.beginTransaction();
            s.save(product);
            s.getTransaction().commit();
        }
        return product;
    }

    @Override
    public Optional<Product> findById(int id) {
        Product product;
        try (Session s = sessionFactory.getCurrentSession()) {
            s.beginTransaction();
            product = s
                    .createQuery("SELECT p FROM Product p WHERE p.id = :id", Product.class)
                    .setParameter("id", id)
                    .getSingleResult();
            s.getTransaction().commit();
        }
        return Optional.ofNullable(product);
    }

    @Override
    public List<Product> findAll() {
        List<Product> products;
        try (Session s = sessionFactory.getCurrentSession()) {
            s.beginTransaction();
            products = s.createQuery("SELECT p FROM Product p", Product.class).getResultList();
            s.getTransaction().commit();
        }
        return products;
    }

    @Override
    public List<Product> findByName(String name) {
        List<Product> product;
        try (Session s = sessionFactory.getCurrentSession()) {
            s.beginTransaction();
            product = s
                    .createQuery("SELECT p FROM Product p WHERE p.name = :name", Product.class)
                    .setParameter("name", name)
                    .getResultList();
            s.getTransaction().commit();
        }
        return product;
    }

    @Override
    public void deleteById(String id) {
        try (Session s = sessionFactory.getCurrentSession()) {
            s.beginTransaction();
            s.createQuery("delete Product p where p.id = :id")
                    .setParameter("id", id);
            s.getTransaction().commit();
        }
    }
}
