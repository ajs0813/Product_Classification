package com.product.controller;

import com.product.domain.ProductCategory;
import com.product.service.ProductCategoryService;
import com.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/categories")
public class ProductCategoryController {
    @Autowired
    private ProductCategoryService productCategoryService;

    // 제품 분류 목록 화면
    @GetMapping
    public String getAllCategories(Model model){
        List<ProductCategory> categoryList = productCategoryService.getAllCategories();
        model.addAttribute("categoryList", categoryList);
        return "productCategoryList";
    }

    // 제품 분류 검색
    @GetMapping("/list")
    public String getCategoriesByName(@RequestParam(required = false) String categoryName, Model model) {
        List<ProductCategory> categoryList;

        if (categoryName != null && !categoryName.trim().isEmpty()) {
            categoryList = productCategoryService.getCategoriesByName(categoryName);
        } else {
            categoryList = productCategoryService.getAllCategories();
        }

        model.addAttribute("categoryList", categoryList);
        return "productCategoryList";
    }

    // 제품 분류 등록 열기
    @GetMapping("/add")
    public String addCategory(Model model) {
        return "productCategoryAdd";
    }

    // 제품 분류 등록 적용
    @PostMapping("/add")
    public ResponseEntity<String> addCategory(
            @RequestParam("categoryCode") String categoryCode,
            @RequestParam("categoryName") String categoryName,
            @RequestParam("deleteYn") String deleteYn
    ) {
        try {
            ProductCategory category = new ProductCategory();
            category.setCategoryCode(categoryCode);
            category.setCategoryName(categoryName);
            category.setDeleteYn(deleteYn);

            productCategoryService.addCategory(category);

            return ResponseEntity.ok("success");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("error");
        }
    }

    // 제품 분류 수정 화면 열기
    @GetMapping("/update/{categoryCode}")
    public String getCategoriesUpdate(@PathVariable String categoryCode, Model model) {
        ProductCategory category = productCategoryService.getCategoryByCode(categoryCode);
        model.addAttribute("category", category);
        return "productCategoryUpdate";
    }

    // 제품 분류 수정 적용
    @PostMapping("/update")
    public ResponseEntity<String> updateCategory(
            @RequestParam("categoryCode") String categoryCode,
            @RequestParam("categoryName") String categoryName,
            @RequestParam("deleteYn") String deleteYn
    ) {
        try {
            ProductCategory category = new ProductCategory();
            category.setCategoryCode(categoryCode);
            category.setCategoryName(categoryName);
            category.setDeleteYn(deleteYn);

            productCategoryService.updateCategory(category);

            return ResponseEntity.ok("success");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("error");
        }
    }

    // 제품 분류 삭제
    @DeleteMapping("/delete/{categoryCode}")
    public ResponseEntity<String> deleteCategory(@PathVariable String categoryCode) {
        try {
            productCategoryService.deleteCategory(categoryCode);
            return ResponseEntity.ok("success");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("error");
        }
    }
}