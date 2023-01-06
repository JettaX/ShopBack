package com.okon.core.repository.specifications;


import com.okon.core.model.Product;
import org.springframework.data.jpa.domain.Specification;

public class ProductsSpecifications {

    public static Specification<Product> priceGreaterThanOrEqualTo(Integer minPrice) {
        return (root, criteriaQuery, criteriaBuilder) ->
                criteriaBuilder.greaterThanOrEqualTo(root.get("price"), minPrice);
    }

    public static Specification<Product> priceLessThanOrEqualTo(Integer maxPrice) {
        return (root, criteriaQuery, criteriaBuilder) ->
                criteriaBuilder.lessThanOrEqualTo(root.get("price"), maxPrice);
    }

    public static Specification<Product> nameStartWith(String name) {
        return (root, criteriaQuery, criteriaBuilder) ->
                criteriaBuilder.like(root.get("name"), name + "%");
    }
}
