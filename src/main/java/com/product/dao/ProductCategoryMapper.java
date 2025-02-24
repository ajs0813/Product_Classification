package com.product.dao;

import com.product.domain.ProductCategory;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProductCategoryMapper {
    // 1. 모든 제품 분류를 조회하는 메서드
    List<ProductCategory> selectAllCategories();

    // 2. 분류명을 기준으로 제품 분류를 검색하는 메서드
    List<ProductCategory> selectCategoriesByName(String categoryName);

    // 3. 새로운 제품 분류를 추가하는 메서드
    void insertCategory(ProductCategory category);

    // 4. 기존 제품 분류를 수정하는 메서드
    void updateCategory(ProductCategory category);

    // 5. 특정 제품 분류를 삭제하는 메서드 (제품 분류 코드로 삭제)
    void deleteCategory(String categoryCode);

    ProductCategory selectCategoryByCode(String categoryCode);
}
