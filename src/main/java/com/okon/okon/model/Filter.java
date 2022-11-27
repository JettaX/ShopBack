package com.okon.okon.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Filter {
    private Integer maxValue = Integer.MAX_VALUE;
    private Integer minValue = 0;
}
