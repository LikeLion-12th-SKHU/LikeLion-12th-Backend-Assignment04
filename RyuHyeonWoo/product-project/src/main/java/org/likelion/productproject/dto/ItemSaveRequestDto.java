package org.likelion.productproject.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ItemSaveRequestDto {

    private Long itemId;
    private String name;
    private Long price;

    @Builder
    public ItemSaveRequestDto(Long itemId, String name, Long price) {
        this.itemId = itemId;
        this.name = name;
        this.price = price;
    }
}
