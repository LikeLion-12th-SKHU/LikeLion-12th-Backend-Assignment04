package org.likelion.productproject.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter //1 Getter 메소드 자동 생성
@NoArgsConstructor  //2 기본 생성자 자동 추가 public Product() {}
public class Product {      //entity 클래스라고 하기도 함
    private Long id;            //상품 아이디
    private Long productId;     //상품 일련번호
    private String name;        //상품명
    private Long price;         //가격

    @Builder
    public Product(Long id, Long productId, String name, Long price) {
        this.id = id;
        this.productId = productId;
        this.name = name;
        this.price = price;
    }

    /*
    entity 클래스에서는 setter 대신 명확히 그 목적과 의도를 나타낼 수 있는 메소드를 추가
     */
    public void update(String name, Long price) {
        this.name = name;
        this.price = price;
    }
}