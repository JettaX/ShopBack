package com.okon.core.service;


import com.okon.core.repository.BoughtProductRepository;
import com.okon.core.model.BoughtProduct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoughtProductServiceImpl implements BoughtProductService {
    private final BoughtProductRepository boughtProductRepository;

    @Override
    public BoughtProduct insert(BoughtProduct product) {
        return boughtProductRepository.save(product);
    }

    @Override
    public List<BoughtProduct> insertAll(List<BoughtProduct> products) {
        return boughtProductRepository.saveAll(products);
    }


}
