package com.okon.core.service;


import com.okon.api.dto.CartItemDTO;
import com.okon.core.integration.CartServiceIntegration;
import com.okon.core.model.BoughtProduct;
import com.okon.core.model.Order;
import com.okon.core.repository.OrdersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Transactional
public class OrdersServiceImpl implements OrdersService {
    private final OrdersRepository ordersRepository;
    private final UserService userService;
    private final CartServiceIntegration cartService;
    private final BoughtProductService boughtProductService;

    @Override
    public Order insert(Order order) {
        return ordersRepository.save(order);
    }

    @Override
    public List<Order> findUserOrders(Long userId) {
        return ordersRepository.findByUserId(userId);
    }

    @Override
    public Long getCountOfBought(Long productId) {
        return ordersRepository.countAllByProductId(productId);
    }

    @Override
    public Optional<Order> findById(Long id) {
        return ordersRepository.findById(id);
    }

    @Override
    public void createOrder(Principal principal) {
        userService.findByUsername(principal.getName()).ifPresent(user ->
                cartService.findByUserId(user.getId()).ifPresent(cart -> {
                    List<BoughtProduct> boughtProducts = boughtProductService
                            .insertAll(convertProductsToBought(cart.getProducts()));
                    Order order = Order.builder()
                            .user(user)
                            .products(boughtProducts)
                            .build();
                    ordersRepository.save(order);
                    cartService.clearByUserId(user.getId());
                }));
    }

    private BoughtProduct convertProductToBought(CartItemDTO cartItem) {
        return BoughtProduct.builder()
                .productId(cartItem.getProduct().getId())
                .name(cartItem.getProduct().getName())
                .price(cartItem.getProduct().getPrice())
                .image(cartItem.getProduct().getImage())
                .quantity(cartItem.getQuantity())
                .build();
    }

    private List<BoughtProduct> convertProductsToBought(Set<CartItemDTO> cartItems) {
        return cartItems.stream().map(this::convertProductToBought).toList();
    }
}
