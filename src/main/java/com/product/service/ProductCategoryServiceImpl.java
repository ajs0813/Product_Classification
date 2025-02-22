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

    @Override
    public List<ProductCategory> getAllCategories(){
        return productCategoryMapper.selectAllCategories();
    }

    @Override
    public List<ProductCategory> getCategoriesByName(String categoryName){
        return productCategoryMapper.selectCategoriesByName(categoryName);
    }

    @Override
    public void addCategory(ProductCategory category){
        productCategoryMapper.insertCategory(category);
    }

    @Override
    public void updateCategory(ProductCategory category){
        productCategoryMapper.updateCategory(category);
    }

    @Override
    public void deleteCategory(String categoryCode){
        productCategoryMapper.deleteCategory(categoryCode);
    }
}
