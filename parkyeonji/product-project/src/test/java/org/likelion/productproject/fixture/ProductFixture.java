package org.likelion.productproject.fixture;

import org.likelion.productproject.domain.Product;
public class ProductFixture {
    public static final Product PRODUCT_1 = new Product(
            1L,
            201914089L,
            "아이패드",
            800000L
    );

    public static final Product PRODUCT_2 = new Product(
            2L,
            202012345L,
            "아이폰",
            1300000L
    );


    public static final Product PRODUCT_3 = new Product(
            3L,
            202100000L,
            "맥북",
            2000000L
    );
}
