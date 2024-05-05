package org.likelion.productproject.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.likelion.productproject.domain.Item;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
public class ItemResponseDto {

    private Long id;
    private Long itemId;
    private String name;
    private Long price;

    public static ItemResponseDto from(Item item) {
        return new ItemResponseDto(item.getId(), item.getItemId(), item.getName(), item.getPrice());
    }
}
