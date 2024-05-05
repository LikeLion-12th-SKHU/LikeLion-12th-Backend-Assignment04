package org.likelion.productproject.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ProductUpdateRequestDto {

    private String name;           //상품명
    private Long price;            //가격 수정

    @Builder
    public ProductUpdateRequestDto(String name, Long price) {
        this.name = name;
        this.price = price;
    }
}