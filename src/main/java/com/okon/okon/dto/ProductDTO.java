package com.okon.okon.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO implements Serializable {
    private Long id;
    private String name;
    private String description;
    private Long price;
    private String image;
}
