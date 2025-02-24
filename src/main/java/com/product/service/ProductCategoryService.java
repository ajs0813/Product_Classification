package com.product.service;

import com.product.domain.ProductCategory;

import java.util.List;

public interface ProductCategoryService {
    List<ProductCategory> getAllCategories();
    List<ProductCategory> getCategoriesByName(String categoryName);
    void addCategory(ProductCategory category);
    void updateCategory(ProductCategory category);
    void deleteCategory(String categoryCode);
    ProductCategory getCategoryByCode(String categoryCode);
}
