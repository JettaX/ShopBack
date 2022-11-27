package com.okon.okon.util;

import com.okon.okon.config.Pictures;
import com.okon.okon.model.Product;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.*;

public class RandomProduct {

    public static List<Product> getRandomProduct(int size) {
        Pictures[] pictures = getPictures(size);
        List<Product> products = new ArrayList<>();

        for (int i = 0, p = 0; i < size; ++i, p++) {
            if (p >= pictures.length) {
                p = 0;
            }
            products.add(Product.builder()
                    .name(RandomStringUtils.random(5))
                    .description(RandomStringUtils.random(150))
                    .price(new Random().nextInt(1000))
                    .image(pictures[p].getDownload_url())
                    .build());
        }
        return products;
    }

    public static List<Product> getNotRandomProduct() {
        Pictures[] pictures = getPictures(5);
        List<Product> products = new ArrayList<>();

        for (int i = 0; i < 5; ++i) {
            products.add(Product.builder()
                    .name("Product_" + i)
                    .description("Description_" + i)
                    .price(new Random().nextInt(1000))
                    .image(pictures[i].getDownload_url())
                    .build());
        }
        return products;
    }

    private static Pictures[] getPictures(int size) {
        String url = "https://picsum.photos/v2/list?page=2&limit=" + size;
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        HttpEntity<Pictures[]> entity = new HttpEntity<>(headers);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Pictures[]> response = restTemplate.exchange(url, HttpMethod.GET, entity, Pictures[].class);
        return response.getBody();
    }
}