package org.likelion.productproject.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.likelion.productproject.domain.Product;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
//AllArgsConstructor은 모든 필드를 포함한 생성자 근데 NoArgsConstructor을 굳이 한 이유는?
//원래 기본 생성자는 자동으로 만들어지지 않나?
public class ProductResponseDto {
    private Long id;
    private Long product_id;
    private String name;
    private Long price;

    public static ProductResponseDto from(Product product) {
        return new ProductResponseDto(product.getId(), product.getProduct_id(),
                product.getName(), product.getPrice());
    }
}
