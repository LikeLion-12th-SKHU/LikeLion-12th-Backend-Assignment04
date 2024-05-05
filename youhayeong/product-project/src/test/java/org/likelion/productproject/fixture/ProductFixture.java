package org.likelion.productproject.fixture;

import org.likelion.productproject.domain.Product;

public class ProductFixture {

    public static final Product PRODUCT_1 = new Product(
            1L,
            10006421L,
            "신라면",
            1500L
    );

    public static final Product PRODUCT_2 = new Product(
            2L,
            20001345L,
            "샌드위치",
            3000L
    );


    public static final Product PRODUCT_3 = new Product(
            3L,
            30004891L,
            "햄버거",
            2500L
    );
}

