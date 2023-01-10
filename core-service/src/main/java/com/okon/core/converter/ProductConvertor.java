package com.okon.core.converter;

import com.okon.api.dto.ProductDTO;
import com.okon.core.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductConvertor {

    public ProductDTO convertToDTO(Product product) {
        return ProductDTO.builder()
                .id(product.getId())
                .name(product.getName())
                .price(product.getPrice())
                .description(product.getDescription())
                .image(product.getImage())
                .build();
    }

    public Page<ProductDTO> convertToDTO(Page<Product> product) {
        return product.map(this::convertToDTO);
    }

    public List<ProductDTO> convertToDTO(List<Product> product) {
        return product.stream().map(this::convertToDTO).toList();
    }

    public Product convertToEntity(ProductDTO productDTO) {
        return Product.builder()
                .id(productDTO.getId())
                .name(productDTO.getName())
                .price(productDTO.getPrice())
                .description(productDTO.getDescription())
                .image(productDTO.getImage())
                .build();
    }
}
