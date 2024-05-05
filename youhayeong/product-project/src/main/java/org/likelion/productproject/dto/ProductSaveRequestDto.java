package org.likelion.productproject.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ProductSaveRequestDto {    //상품 등록

    private Long productId;             //상품 일련 번호
    private String name;                //상품명
    private Long price;                 //가격을 요청으로 받음

    @Builder
    public ProductSaveRequestDto(Long productId, String name, Long price) {
        this.productId = productId;
        this.name = name;
        this.price = price;
    }
}