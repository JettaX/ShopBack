package com.okon.okon.service;

import com.okon.okon.model.BoughtProduct;

import java.util.List;

public interface BoughtProductService {

    BoughtProduct insert(BoughtProduct product);

    List<BoughtProduct> insertAll(List<BoughtProduct> products);
}
