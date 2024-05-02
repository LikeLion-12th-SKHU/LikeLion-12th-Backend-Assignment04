package org.likelion.productproject.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ProductSaveRequestDto {

    private Long productId;
    private String name;
    private long price;

    @Builder
    public ProductSaveRequestDto(Long productId, String name, long price) {
        this.productId = productId;
        this.name = name;
        this.price = price;
    }
}
