package com.product.controller;

import com.product.domain.ProductCategory;
import com.product.service.ProductCategoryService;
import com.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class ProductCategoryController {
    @Autowired
    private ProductCategoryService productCategoryService;
    @Autowired
    private ProductService productService;

    @GetMapping
    public List<ProductCategory> getAllCategories(){
        return productCategoryService.getAllCategories();
    }

    @GetMapping("/search")
    public List<ProductCategory> getCategoriesByName(@RequestParam String categoryName){
        return productCategoryService.getCategoriesByName(categoryName);
    }

    @PostMapping
    public void addCategory(@RequestBody ProductCategory category){
        productCategoryService.addCategory(category);
    }

    @PutMapping("/{categoryCode}")
    public void updateCategory(@PathVariable String categoryCode, @RequestBody ProductCategory category){
        category.setCategoryCode(categoryCode);
        productCategoryService.updateCategory(category);
    }

    @DeleteMapping("/{categoryCode}")
    public void deleteCategory(@PathVariable String categoryCode){
        productCategoryService.deleteCategory(categoryCode);
    }
}
