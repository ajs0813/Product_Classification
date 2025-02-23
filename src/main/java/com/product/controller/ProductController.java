package com.product.controller;

import com.product.domain.Product;
import com.product.domain.ProductCategory;
import com.product.service.ProductCategoryService;
import com.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductService productService;
    @Autowired
    private ProductCategoryService productCategoryService;

    // 제품 목록 화면
    @GetMapping
    public String getAllProducts(Model model) {
        List<Product> productList = productService.getAllProducts();
        model.addAttribute("productList", productList);
        model.addAttribute("categoryList", productCategoryService.getAllCategories());
        return "productList"; // productList.jsp
    }

}
