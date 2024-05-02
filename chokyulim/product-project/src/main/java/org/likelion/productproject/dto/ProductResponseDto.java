package org.likelion.productproject.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.likelion.productproject.domain.Product;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE) // 모든 매개변수 받는 생성자
@NoArgsConstructor
public class ProductResponseDto {

    private Long id;
    private Long productId;
    private String name;
    private Long price;

    public static ProductResponseDto from(Product product) { // 메소드로 생성자 만듦 (이름이 있기 때문에 목적을 나타낼 수 있음)
        return new ProductResponseDto(product.getId(), product.getProductId(), product.getName(), product.getPrice());
    }

}
