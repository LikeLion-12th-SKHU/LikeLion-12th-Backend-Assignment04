package com.example.productproject.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ProductUpdateRequestDto {
    private String productName;
    private Long productPrice;

    @Builder
    public ProductUpdateRequestDto(String productName, Long productPrice){
        this.productName = productName;
        this.productPrice = productPrice;
    }
}
