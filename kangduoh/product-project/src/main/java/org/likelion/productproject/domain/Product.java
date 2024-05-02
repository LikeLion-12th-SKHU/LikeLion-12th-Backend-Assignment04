package org.likelion.productproject.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class Product {

    private Long id;
    private Long productId;
    private String name;
    private long price;

    @Builder
    public Product(Long id, Long productId, String name, long price) {
        this.id = id;
        this.productId = productId;
        this.name = name;
        this.price = price;
    }

    public void update(String name, long price) {
        this.name = name;
        this.price = price;
    }
}
