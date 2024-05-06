package com.example.productproject.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class Product {
    private Long productId;
    private Long productSerialNumber;
    private Long productPrice;
    private String productName;

    @Builder
    public Product(Long productId, Long productSerialNumber, Long productPrice, String productName) {
        this.productId = productId;
        this.productSerialNumber = productSerialNumber;
        this.productPrice = productPrice;
        this.productName = productName;
    }

    public void register(String productName, Long productPrice) {
        this.productName = productName;
        this.productPrice = productPrice;
    }
}