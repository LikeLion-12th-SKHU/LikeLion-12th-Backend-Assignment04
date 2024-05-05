package org.likelion.productproject.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor

public class Product { //강의자료에서 왜 래퍼 클래스인지 질문
    private Long id;
    private Long product_id;
    private String name;
    private Long price;

    @Builder
    public Product(Long id, Long product_id, String name, Long price) {
        this.id = id;
        this.product_id = product_id;
        this.name = name;
        this.price = price;
    }

    public void update(String name, Long price) {
        this.name = name;
        this.price = price;
    }

}
