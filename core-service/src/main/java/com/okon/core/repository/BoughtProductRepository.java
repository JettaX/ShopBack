package com.okon.core.repository;


import com.okon.core.model.BoughtProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoughtProductRepository extends JpaRepository<BoughtProduct, Long> {
}
