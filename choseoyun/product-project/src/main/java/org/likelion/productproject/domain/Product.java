//Domain Model: 개발 대상을 표현하는 모델
//상품 속성 : 상품 아이디, 상품 일련번호, 상품명, 가격을 포함

package org.likelion.productproject.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter //1, 클래스 내 모든 필드의 Getter 메소드를 생성
@NoArgsConstructor //2, 기본 생성자 자동 추가
public class Product {

    private Long id; //상품 아이디
    private Long productId; // 상품 일련번호
    private String name; //상품명
    private Long price; // 가격

        @Builder //3
        public Product(Long id, Long productId, String name, Long price) {
            this.id = id;
            this.productId = productId;
            this.name = name;
            this.price = price;
        }

        public void update(String name, Long price) {
            this.name = name;
            this.price = price;
        }
    }

