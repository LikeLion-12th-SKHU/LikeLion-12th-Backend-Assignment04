package org.likelion.productproject.fixture;

import org.likelion.productproject.domain.Product;

public class ProductFixture {

    public static final Product PRODUCT_1 = new Product(
            1L,
            1001L,
            "아이폰13 미니",
            950_000
    );

    public static final Product PRODUCT_2 = new Product(
            2L,
            1002L,
            "커세어 k70",
            200_000
    );

    public static final Product PRODUCT_3 = new Product(
            3L,
            1003L,
            "몬스터에너지 울트라",
            2300
    );
}
