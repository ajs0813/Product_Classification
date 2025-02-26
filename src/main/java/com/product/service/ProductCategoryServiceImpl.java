package com.product.service;

import com.product.dao.ProductCategoryMapper;
import com.product.domain.ProductCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductCategoryServiceImpl implements ProductCategoryService {
    @Autowired
    private ProductCategoryMapper productCategoryMapper;

    // 제품 분류 목록 서비스 구현
    @Override
    public List<ProductCategory> getAllCategories(){
        return productCategoryMapper.selectAllCategories();
    }

    // 제품 분류명 검색 서비스 구현
    @Override
    public List<ProductCategory> getCategoriesByName(String categoryName){
        return productCategoryMapper.selectCategoriesByName(categoryName);
    }

    // 제품 분류 등록 서비스 구현
    @Override
    public void addCategory(ProductCategory category){
        productCategoryMapper.insertCategory(category);
    }

    // 제품 분류 수정 화면 서비스 구현
    @Override
    public ProductCategory getCategoryByCode(String categoryCode) {
        return productCategoryMapper.selectCategoryByCode(categoryCode);
    }

    // 제품 수정 서비스 구현
    @Override
    public void updateCategory(ProductCategory category){
        productCategoryMapper.updateCategory(category);
    }

    // 제품 삭제 서비스 구현
    @Override
    public void deleteCategory(String categoryCode){
        productCategoryMapper.deleteCategory(categoryCode);
    }
}
