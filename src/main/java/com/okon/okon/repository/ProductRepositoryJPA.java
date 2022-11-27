package com.okon.okon.repository;

import com.okon.okon.model.Filter;
import com.okon.okon.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class ProductRepositoryJPA implements ProductRepositoryCustom {
    @Autowired
    private EntityManager em;

    @Override
    public List<Product> findAllByFilter(Filter filter) {
        return em
                .createQuery("SELECT p FROM Product p where p.price < :max and p.price > :min", Product.class)
                .setParameter("max", filter.getMaxValue())
                .setParameter("min", filter.getMinValue())
                .getResultList();
    }
}
