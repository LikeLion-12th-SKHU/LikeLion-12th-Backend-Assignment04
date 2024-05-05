package org.likelion.productproject.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ProductSaveRequestDto {

    private String name;        //상품의 이름
    private Long productId;     //상품 일련번호
    private Long price;         //상품의 가격

    @Builder
    public ProductSaveRequestDto(String name, Long productId, Long price) {
        this.name = name;
        this.productId = productId;
        this.price = price;
    }
}
