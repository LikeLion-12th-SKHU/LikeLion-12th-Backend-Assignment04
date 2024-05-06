package org.likelion.productproject.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.likelion.productproject.domain.Product;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE) // 1
@NoArgsConstructor
public class ProductResponseDto {

    private String name;        //상품의 이름
    private Long id;            //상품의 아이디
    private Long productId;     //상품 일련번호
    private Long price;         //상품의 가격

    public static ProductResponseDto from(Product product) {
        return new ProductResponseDto(product.getName() ,product.getId(), product.getProductId(),
                product.getPrice());
    }
}
