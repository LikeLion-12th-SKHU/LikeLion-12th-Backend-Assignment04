package org.likelion.productproject.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import org.likelion.productproject.domain.Product;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor

public class ProductResponseDto {

    private Long id; //상품 아이디
    private Long productId; // 상품 일련번호
    private String name; //상품명
    private Long price; // 가격

    public static ProductResponseDto from (Product product){
            return new ProductResponseDto(product.getId(), product.getProductId(), product.getName(), product.getPrice());
    }
}
