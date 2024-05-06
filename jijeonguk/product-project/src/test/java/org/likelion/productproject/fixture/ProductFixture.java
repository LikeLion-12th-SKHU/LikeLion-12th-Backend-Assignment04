package org.likelion.productproject.fixture;

import org.likelion.productproject.domain.Product;

public class ProductFixture {

    public static final Product PRODUCT_1 = new Product(
            1L,
            20240001L,
            "콜라",
            1800L
    );
    public static final Product PRODUCT_2 = new Product(
            2L,
            20240002L,
            "사이다",
            1500L
    );
    public static final Product PRODUCT_3 = new Product(
            3L,
            20240003L,
            "환타",
            1400L
    );
}
