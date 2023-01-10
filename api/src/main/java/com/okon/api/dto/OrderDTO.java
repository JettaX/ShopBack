package com.okon.api.dto;

import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO {
    private Long id;
    private String username;
    private List<BoughtProductDTO> products;
    private BigDecimal totalPrice;
}
