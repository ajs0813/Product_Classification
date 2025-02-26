package com.product.dao;

import com.product.domain.ProductCategory;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProductCategoryMapper {
    // 제품 분류 목록 메서드
    List<ProductCategory> selectAllCategories();

    // 제품 분류 검색 메서드
    List<ProductCategory> selectCategoriesByName(String categoryName);

    // 제품 분류 등록 메서드
    void insertCategory(ProductCategory category);

    // 제품 분류 수정 화면 메서드
    ProductCategory selectCategoryByCode(String categoryCode);

    // 제품 분류 수정 메서드
    void updateCategory(ProductCategory category);

    // 제품 분류 삭제 메서드
    void deleteCategory(String categoryCode);
}
