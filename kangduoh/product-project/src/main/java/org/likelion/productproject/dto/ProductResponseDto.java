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

    private Long id;
    private Long productId;
    private String name;
    private long price;

    public static ProductResponseDto from(Product product) {
        return new ProductResponseDto(product.getId(), product.getProductId(), product.getName(), product.getPrice());
    }
}
