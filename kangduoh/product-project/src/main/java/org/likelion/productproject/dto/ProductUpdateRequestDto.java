package org.likelion.productproject.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ProductUpdateRequestDto {

    private String name;
    private long price;

    @Builder
    public ProductUpdateRequestDto(String name, long price) {
        this.name = name;
        this.price = price;
    }
}
