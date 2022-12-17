package com.okon.okon.repository;

import com.okon.okon.model.BoughtProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoughtProductRepository extends JpaRepository<BoughtProduct, Long> {
}
