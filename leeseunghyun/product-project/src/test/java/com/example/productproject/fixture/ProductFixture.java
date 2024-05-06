package com.example.productproject.fixture;

import com.example.productproject.domain.Product;

public class ProductFixture {


    public static final Product PRODUCT_1 = new Product(
            1L,
            200001L,
            500L,
            "물"
    );

    public static final Product PRODUCT_2 = new Product(
            2L,
            200002L,
            800L,
            "차가운 아메리카노"
    );

    public static final Product PRODUCT_3 = new Product(
            3L,
            200003L,
            1200L,
            "뜨거운 아메리카노"
    );



}

