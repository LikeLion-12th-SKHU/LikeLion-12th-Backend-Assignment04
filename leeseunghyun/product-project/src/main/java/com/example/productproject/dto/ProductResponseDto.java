package com.example.productproject.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import com.example.productproject.domain.Product;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponseDto {
    private Long productId;
    private Long productSerialNumber;
    private Long productPrice;
    private String productName;

    public static ProductResponseDto from(Product product){
        return new ProductResponseDto(product.getProductId(), product.getProductSerialNumber(), product.getProductPrice(), product.getProductName());
    }
}
