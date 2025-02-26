package com.product.service;

import com.product.domain.ProductCategory;

import java.util.List;

public interface ProductCategoryService {
    // 제품 분류 목록 서비스
    List<ProductCategory> getAllCategories();

    // 제품 분류명 검색 서비스
    List<ProductCategory> getCategoriesByName(String categoryName);

    // 제품 분류 등록 서비스
    void addCategory(ProductCategory category);

    // 제품 분류 수정 화면 서비스
    ProductCategory getCategoryByCode(String categoryCode);

    // 제품 수정 서비스
    void updateCategory(ProductCategory category);

    // 제품 삭제 서비스
    void deleteCategory(String categoryCode);

}
