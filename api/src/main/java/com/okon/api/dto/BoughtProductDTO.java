package com.okon.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BoughtProductDTO {
    private Long id;
    private Long productId;
    private String name;
    private BigDecimal price;
    private String image;
    private Integer quantity;
    private BigDecimal totalPrice;
}
