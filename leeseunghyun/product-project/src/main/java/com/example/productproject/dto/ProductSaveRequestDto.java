package com.example.productproject.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ProductSaveRequestDto {

    private Long productSerialNumber;
    private Long productPrice;
    private String productName;
    @Builder
    public ProductSaveRequestDto(Long productSerialNumber, Long productPrice, String productName) {
        this.productSerialNumber = productSerialNumber;
        this.productPrice = productPrice;
        this.productName = productName;
    }
}
