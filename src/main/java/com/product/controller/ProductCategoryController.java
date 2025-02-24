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

    @GetMapping
    public String getAllCategories(Model model){
        List<ProductCategory> categoryList = productCategoryService.getAllCategories();
        model.addAttribute("categoryList", categoryList);
        return "productCategoryList";
    }

    // 카테고리명 검색
    @GetMapping("/list")
    public String getCategoriesByName(@RequestParam(required = false) String categoryName, Model model) {
        List<ProductCategory> categoryList;

        if (categoryName != null && !categoryName.trim().isEmpty()) {
            categoryList = productCategoryService.getCategoriesByName(categoryName);
        } else {
            categoryList = productCategoryService.getAllCategories();
        }

        model.addAttribute("categoryList", categoryList);
        return "productCategoryList"; // productCategoryList.jsp로 이동
    }


    // 카테고리 등록 화면 열기
    @GetMapping("/add")
    public String addCategory(Model model) {
        return "productCategoryAdd"; // productCategoryAdd.jsp로 이동
    }

    // 카테고리 등록 처리
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

            // 성공 응답 반환
            return ResponseEntity.ok("success");
        } catch (Exception e) {
            e.printStackTrace();
            // 실패 응답 반환
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("error");
        }
    }

    // 카테고리 수정 화면 열기
    @GetMapping("/update/{categoryCode}")
    public String showUpdateForm(@PathVariable String categoryCode, Model model) {
        ProductCategory category = productCategoryService.getCategoryByCode(categoryCode);
        model.addAttribute("category", category);
        return "productCategoryUpdate"; // productCategoryUpdate.jsp로 이동
    }

    // 카테고리 수정 처리
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

            productCategoryService.updateCategory(category); // 수정된 카테고리 저장

            return ResponseEntity.ok("success"); // 성공 응답
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("error"); // 실패 응답
        }
    }

    // 카테고리 삭제 처리
    @DeleteMapping("/delete/{categoryCode}")
    public ResponseEntity<String> deleteCategory(@PathVariable String categoryCode) {
        try {
            productCategoryService.deleteCategory(categoryCode); // 카테고리 삭제
            return ResponseEntity.ok("success"); // 성공 응답
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("error"); // 실패 응답
        }
    }
}