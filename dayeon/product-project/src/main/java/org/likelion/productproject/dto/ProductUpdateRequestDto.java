package org.likelion.productproject.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ProductUpdateRequestDto {

    private String name;
    private String price;

    @Builder
    public ProductUpdateRequestDto(String name, String price) {
        this.name = name;
        this.price = price;
    }
}
