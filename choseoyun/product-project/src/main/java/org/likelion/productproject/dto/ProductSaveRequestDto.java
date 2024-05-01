//DTOs - 계층 간 데이터 이동에 사용되는 운반 역할을 한다.
// 상품 등록 - 상품 일련번호, 상품명, 가격 을 요청으로 받음

package org.likelion.productproject.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Builder;

@Getter
@NoArgsConstructor
public class ProductSaveRequestDto {

    private Long productId; // 상품 일련번호
    private Long price; // 가격
    private String name; //상품명

    @Builder

    public ProductSaveRequestDto(Long productId, Long price, String name) {
        this.productId = productId;
        this.price = price;
        this.name = name;
    }
}