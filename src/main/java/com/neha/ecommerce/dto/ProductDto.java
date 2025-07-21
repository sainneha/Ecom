// --- dto/ProductDto.java ---
package com.neha.ecommerce.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {
    private String name;
    private String description;
    private Double price;
    private Integer stock;
}