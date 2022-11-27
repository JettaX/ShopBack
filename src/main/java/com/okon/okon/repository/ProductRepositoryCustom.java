package com.okon.okon.repository;

import com.okon.okon.model.Filter;
import com.okon.okon.model.Product;

import java.util.List;

public interface ProductRepositoryCustom {
    List<Product> findAllByFilter(Filter filter);
}
