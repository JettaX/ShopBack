package com.okon.core.util;

import com.okon.core.model.Pictures;
import com.okon.core.model.Product;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class RandomProduct {

    private static String[] randomProducts = {"lemon", "apple", "orange", "banana", "pineapple", "watermelon", "grapefruit", "grapes", "strawberry", "raspberry", "blueberry", "blackberry", "kiwi", "pear", "peach", "plum", "apricot", "cherry", "mango", "coconut", "avocado", "tomato", "potato", "cucumber", "onion", "garlic", "carrot", "broccoli", "cauliflower", "spinach", "lettuce", "cabbage", "celery", "asparagus", "mushroom", "peas", "beans", "corn", "sweet potato", "eggplant", "zucchini", "pumpkin", "squash", "beetroot", "radish", "turnip", "ginger", "garlic", "leek", "cress", "artichoke", "olive", "pepper", "chili"};

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
                    .price(new BigDecimal(new Random().nextDouble(1000)))
                    .image(pictures[p].getDownload_url())
                    .build());
        }
        return products;
    }

    public static List<Product> getNotRandomProduct() {
        Pictures[] pictures = getPictures(randomProducts.length);
        List<Product> products = new ArrayList<>();

        for (int i = 0; i < randomProducts.length; ++i) {
            products.add(Product.builder()
                    .name(randomProducts[i])
                    .description("Description_" + i)
                    .price(new BigDecimal(new Random().nextDouble(1000)))
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