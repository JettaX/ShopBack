package com.okon.core.endpoint;

import com.okon.core.soap.product.GetProductsRequest;
import com.okon.core.soap.product.GetProductsResponse;
import com.okon.core.service.ProductServiceSoap;
import lombok.RequiredArgsConstructor;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
@RequiredArgsConstructor
public class ProductEndpoint {
    private final static String NAMESPACE_URI = "http://www.okon.com/okon/products";
    private final ProductServiceSoap productServiceSoap;

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getProductsRequest")
    @ResponsePayload
    public GetProductsResponse getProducts(@RequestPayload GetProductsRequest request) {
        GetProductsResponse response = new GetProductsResponse();
        productServiceSoap.getProducts().forEach(response.getProducts()::add);
        return response;
    }
}
