package org.likelion.productproject.fixture;

import org.likelion.productproject.domain.Product;
import org.likelion.productproject.service.ProductService;

public class ProductFixture {

    public static final Product PRODUCT_1 = new Product(
            1L,
            757806L,
            "아디다스 Samba",
            129000L
    );

    public static final Product PRODUCT_2 = new Product(
            2L,
            1201967L,
            "아식스 Jog",
            108000L
    );

    public static final Product PRODUCT_3 = new Product(
            3L,
            2920111L,
            "나이키 Air Force",
            90000L
    );
}
