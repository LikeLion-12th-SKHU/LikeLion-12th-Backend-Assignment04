package org.likelion.productproject.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class Item {

    private Long id;
    private Long itemId;
    private String name;
    private Long price;

    @Builder
    public Item(Long id, Long itemId, String name, Long price) {
        this.id = id;
        this.itemId = itemId;
        this.name = name;
        this.price = price;
    }

    public void update(String name, Long price) {
        this.name = name;
        this.price = price;
    }

}
