//상품 수정 - 상품명, 가격만 수정 가능

package org.likelion.productproject.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Builder;

@Getter
@NoArgsConstructor
public class ProductUpdateRequestDto {

    private Long price; // 가격
    private String name; //상품명

    @Builder

    public ProductUpdateRequestDto(String name, Long price) {
        this.name = name;
        this.price = price;
    }
}