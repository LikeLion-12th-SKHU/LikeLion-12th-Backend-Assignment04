package org.likelion.productproject.fixture;

import org.likelion.productproject.domain.Product;
public class ProductFixture {
    public static final Product PRODUCT_1 = new Product(
            1L,
            11110000L,
            "과자",
            1000L

    );

    public static final Product PRODUCT_2 = new Product(
            2L,
            22223333L,
            "음료수",
            2000L
    );


    public static final Product PRODUCT_3 = new Product(
            3L,
            44445555L,
            "젤리",
            3000L
    );
}
