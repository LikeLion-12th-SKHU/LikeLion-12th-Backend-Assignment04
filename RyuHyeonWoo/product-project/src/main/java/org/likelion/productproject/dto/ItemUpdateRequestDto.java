package org.likelion.productproject.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@NoArgsConstructor

public class ItemUpdateRequestDto {
    private String name;
    private Long price;

    public ItemUpdateRequestDto(String name, Long price) {
        this.name = name;
        this.price = price;
    }
}
