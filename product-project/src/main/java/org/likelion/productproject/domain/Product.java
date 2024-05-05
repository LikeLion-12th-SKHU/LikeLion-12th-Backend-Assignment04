package org.likelion.productproject.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class Product {

    private Long id;            //상품의 아이디
    private Long productId;     //상품 일련번호
    private Long price;         //상품의 가격
    private String name;        //상품의 이름

    @Builder // 3
    public Product(String name,Long id, Long productId, Long price){
        this.name = name;
        this.id = id;
        this.productId = productId;
        this.price = price;

    }

    public void updateProduct(String name ,Long price){
        this.name = name;
        this.price = price;
    }
}
