package org.likelion.productproject.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ProductUpdateRequestDto {

    private String name;        //상품의 이름
    private Long price;         //상품의 가격

    @Builder
    public ProductUpdateRequestDto(String name, Long price) {
        this.name = name;
        this.price = price;
    }
}
