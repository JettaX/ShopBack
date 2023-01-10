package com.okon.core.converter;

import com.okon.api.dto.OrderDTO;
import com.okon.core.model.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class OrderConvertor {
    private final BoughtProductConvertor boughtProductConvertor;

    public OrderDTO convertToDTO(Order order) {
        return OrderDTO.builder()
                .id(order.getId())
                .username(order.getUsername())
                .products(boughtProductConvertor.convertToDTO(order.getProducts()))
                .totalPrice(order.getTotalPrice())
                .build();
    }

    public List<OrderDTO> convertToDTO(List<Order> order) {
        return order.stream().map(this::convertToDTO).toList();
    }

    public Order convertToEntity(OrderDTO orderDTO) {
        return Order.builder()
                .id(orderDTO.getId())
                .username(orderDTO.getUsername())
                .products(boughtProductConvertor.convertToEntity(orderDTO.getProducts()))
                .totalPrice(orderDTO.getTotalPrice())
                .build();
    }
}
