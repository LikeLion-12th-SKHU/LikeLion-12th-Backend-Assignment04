package org.likelion.productproject.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ProductUpdateRequestDto {

    private String name;
    private Long price;
    // 테스트 코드 long 때매 오류난 거 아님
    @Builder
    public ProductUpdateRequestDto(String name, Long price) {
        this.name = name;
        this.price = price;
    }
}