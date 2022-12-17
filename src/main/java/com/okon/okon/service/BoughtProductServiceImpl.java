package com.okon.okon.service;

import com.okon.okon.model.BoughtProduct;
import com.okon.okon.repository.BoughtProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BoughtProductServiceImpl implements BoughtProductService {
    private final BoughtProductRepository boughtProductRepository;

    @Override
    public BoughtProduct insert(BoughtProduct product) {
        return boughtProductRepository.save(product);
    }
}
