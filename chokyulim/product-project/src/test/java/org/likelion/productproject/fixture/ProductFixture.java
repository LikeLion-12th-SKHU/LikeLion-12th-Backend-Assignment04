package org.likelion.productproject.fixture;

import org.likelion.productproject.domain.Product;

public class ProductFixture {

    public static final Product PRODUCT_1 = new Product(
            1L,
            100001L,
            "사과",
            1000L
    );

    public static final Product PRODUCT_2 = new Product(
            2L,
            100002L,
            "파인애플",
            1500L
    );


    public static final Product PRODUCT_3 = new Product(
            3L,
            100003L,
            "귤",
            2000L
    );
}
