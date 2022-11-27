package com.okon.okon.repository;

import com.okon.okon.model.BoughtProduct;
import com.okon.okon.model.Order;
import com.okon.okon.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class OrdersRepositoryJPA implements OrdersRepository {
    @PersistenceContext
    private EntityManager em;

    @Override
    public Order insert(Order order) {
        return em.merge(order);
    }

    @Override
    public BoughtProduct insertBoughtProduct(BoughtProduct product) {
        return em.merge(product);
    }

    @Override
    public List<Order> findByUserId(Long userId) {
        return em
                .createQuery("select order from Order order where order.user.id = :id",
                        Order.class)
                .setParameter("id", userId)
                .getResultList();
    }

    @Override
    public Optional<Order> findById(Long id) {
        Order order = em
                .createQuery("SELECT o FROM Order o WHERE o.id = :id", Order.class)
                .setParameter("id", id)
                .getSingleResult();
        return Optional.ofNullable(order);
    }

    @Override
    public List<User> findUsersByProduct(Long productId) {
        return em
                .createQuery("FROM Order as o join o.products as p on p.id = :id")
                .setParameter("id", productId)
                .getResultList();
    }

    @Override
    public Long getCountOfBought(Long productId) {
        return (Long) em.createQuery("select count (*) FROM Order o join o.products as p on p.id = :id")
                .setParameter("id", productId)
                .getSingleResult();
    }
}
