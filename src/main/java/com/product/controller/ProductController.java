package com.product.controller;

import com.product.domain.Product;
import com.product.domain.ProductCategory;
import com.product.service.ProductCategoryService;
import com.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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


    // 제품명 검색
    @GetMapping("/list")
    public String getProductList(@RequestParam(required = false) String productName, Model model){
        List<Product> productList;

        if(productName != null && !productName.trim().isEmpty()){
            productList = productService.getProductsByName(productName);
        } else {
            productList = productService.getAllProducts();
        }

        model.addAttribute("productList", productList);
        return "productList";
    }

    // 제품 등록 열기
    @GetMapping("/add")
    public String addProduct(Model model){
        List<ProductCategory> categoryList = productCategoryService.getAllCategories();
        model.addAttribute("categoryList", categoryList);
        return "productAdd";
    }

    // 제품 등록 적용
    @PostMapping("/add")
    public ResponseEntity<String> addProduct(
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
        try {
            String fileName = null;
            if (productImage != null && !productImage.isEmpty()) {
                fileName = productImage.getOriginalFilename();
                Path uploadPath = Paths.get("C:/Users/AHN/IdeaProjects/Product_Classification/src/main/resources/static/images/");
                Files.createDirectories(uploadPath);
                Files.copy(productImage.getInputStream(), uploadPath.resolve(fileName), StandardCopyOption.REPLACE_EXISTING);
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
            product.setProductImage(fileName);

            productService.addProduct(product);

            // 성공 응답 반환
            return ResponseEntity.ok("success");
        } catch (IOException e) {
            e.printStackTrace();
            // 실패 응답 반환
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("error");
        }
    }

    // 제품 수정 화면 열기
    @GetMapping("/update/{productCode}")
    public String showUpdateForm(@PathVariable String productCode, Model model) {
        // 제품 정보 조회
        Product product = productService.getProductsByCode(productCode);
        List<ProductCategory> categoryList = productCategoryService.getAllCategories();

        // 모델에 데이터 추가
        model.addAttribute("product", product);
        model.addAttribute("categoryList", categoryList);

        return "productUpdate"; // productUpdateForm.jsp로 이동
    }

    // 제품 수정 적용
    @PostMapping("/update")
    public ResponseEntity<String> updateProduct(
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
        try {
            String fileName = null;
            if (productImage != null && !productImage.isEmpty()) {
                fileName = productImage.getOriginalFilename();
                Path uploadPath = Paths.get("C:/Users/AHN/IdeaProjects/Product_Classification/src/main/resources/static/images/");
                Files.createDirectories(uploadPath);
                Files.copy(productImage.getInputStream(), uploadPath.resolve(fileName), StandardCopyOption.REPLACE_EXISTING);
            }

            // 제품 객체 업데이트
            Product product = productService.getProductsByCode(productCode);
            product.setProductName(productName);
            product.setCategoryCode(categoryCode);
            product.setProductDate(productDate);
            product.setUnitPrice(unitPrice);
            product.setOperationYn(operationYn);
            product.setManufactureAddress(manufactureAddress);
            product.setProductionDescription(productionDescription);
            if (fileName != null) {
                product.setProductImage(fileName);
            }

            productService.updateProduct(product); // 수정된 제품 정보 저장

            // 성공 응답 반환
            return ResponseEntity.ok("success");
        } catch (IOException e) {
            e.printStackTrace();
            // 실패 응답 반환
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("error");
        }
    }
    // 제품 삭제
    @DeleteMapping("/delete/{productCode}")
    public ResponseEntity<String> deleteProduct(@PathVariable String productCode) {
        try {
            productService.deleteProduct(productCode); // 제품 삭제
            return ResponseEntity.ok("success"); // 성공 응답
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("error"); // 실패 응답
        }
    }
}