package com.okon.okon.repository;

import com.okon.okon.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface OrdersRepository extends JpaRepository<Order, Long> {
    List<Order> findByUserId(Long userId);
    Optional<Order> findById(Long id);
    @Query(value = "select count(p.id) FROM Order o join o.products as p on p.id = :id")
    Long countAllByProductId(@Param("id") Long productId);
}
