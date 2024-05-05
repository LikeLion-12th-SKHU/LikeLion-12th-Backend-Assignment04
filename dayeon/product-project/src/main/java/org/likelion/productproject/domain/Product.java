package org.likelion.productproject.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class Product {

    private Long id; //상품 아이디
    private Long productId; //상품 일련번호
    private String name; //상품명
    private String price; //가격

    @Builder
    public Product(Long id, Long productId, String name, String price) {
        this.id = id;
        this.productId = productId;
        this.name = name;
        this.price = price;
    }

    public void update(String name, String price) {
        this.name = name;
        this.price = price;
    }
}
