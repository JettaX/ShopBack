package com.okon.core.converter;

import com.okon.api.dto.BoughtProductDTO;
import com.okon.core.model.BoughtProduct;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BoughtProductConvertor {

        public BoughtProductDTO convertToDTO(BoughtProduct boughtProduct) {
            return BoughtProductDTO.builder()
                    .id(boughtProduct.getId())
                    .name(boughtProduct.getName())
                    .price(boughtProduct.getPrice())
                    .image(boughtProduct.getImage())
                    .quantity(boughtProduct.getQuantity())
                    .productId(boughtProduct.getProductId())
                    .totalPrice(boughtProduct.getTotalPrice())
                    .build();
        }

    public List<BoughtProductDTO> convertToDTO(List<BoughtProduct> boughtProducts) {
        return boughtProducts.stream().map(this::convertToDTO).toList();
    }

        public BoughtProduct convertToEntity(BoughtProductDTO boughtProductDTO) {
            return BoughtProduct.builder()
                    .id(boughtProductDTO.getId())
                    .name(boughtProductDTO.getName())
                    .price(boughtProductDTO.getPrice())
                    .image(boughtProductDTO.getImage())
                    .quantity(boughtProductDTO.getQuantity())
                    .productId(boughtProductDTO.getProductId())
                    .totalPrice(boughtProductDTO.getTotalPrice())
                    .build();
        }
    public List<BoughtProduct> convertToEntity(List<BoughtProductDTO> boughtProducts) {
        return boughtProducts.stream().map(this::convertToEntity).toList();
    }

}
