package com.product.service;

import com.product.domain.Product;

import java.util.List;

public interface ProductService {
    // 제품 목록 서비스
    List<Product> getAllProducts();

    // 제품명 검색 서비스
    List<Product> getProductsByName(String productName);

    // 제품 등록 서비스
    void addProduct(Product product);

    // 제품 수정 화면 서비스
    Product getProductsByCode(String productCode);

    // 제품 수정 서비스
    void updateProduct(Product product);

    // 제품 삭제 서비스
    void deleteProduct(String productCode);
}
