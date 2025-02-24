package com.product.controller;

import com.product.domain.Product;
import com.product.domain.ProductCategory;
import com.product.service.ProductCategoryService;
import com.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
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

    @PostMapping("/add")
    public String addProduct(
            @RequestParam("productCode") String productCode,
            @RequestParam("productName") String productName,
            @RequestParam("categoryCode") String categoryCode,
            @RequestParam("productDate") String productDate,
            @RequestParam("unitPrice") int unitPrice,
            @RequestParam("operationYn") String operationYn,
            @RequestParam("manufactureAddress") String manufactureAddress,
            @RequestParam("productionDescription") String productionDescription,
            @RequestParam(value = "productImage", required = false) MultipartFile productImage
    ) {
        String fileName = null;
        if (productImage != null && !productImage.isEmpty()) {
            try {
                fileName = productImage.getOriginalFilename();
                Path uploadPath = Paths.get("C:/Users/AHN/IdeaProjects/Product_Classification/src/main/resources/static/images/"); // 저장 경로
                Files.createDirectories(uploadPath); // 디렉토리 없으면 생성
                Files.copy(productImage.getInputStream(), uploadPath.resolve(fileName), StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // Product 객체 생성 후 서비스로 전달
        Product product = new Product();
        product.setProductCode(productCode);
        product.setProductName(productName);
        product.setCategoryCode(categoryCode);
        product.setProductDate(productDate);
        product.setUnitPrice(unitPrice);
        product.setOperationYn(operationYn);
        product.setManufactureAddress(manufactureAddress);
        product.setProductionDescription(productionDescription);
        product.setProductImage(fileName); // 저장된 파일명 사용

        productService.addProduct(product);

        return "redirect:/products";
    }
}

