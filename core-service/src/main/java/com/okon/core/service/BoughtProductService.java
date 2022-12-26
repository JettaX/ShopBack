package com.okon.core.service;


import com.okon.core.model.BoughtProduct;

import java.util.List;

public interface BoughtProductService {

    BoughtProduct insert(BoughtProduct product);

    List<BoughtProduct> insertAll(List<BoughtProduct> products);
}
