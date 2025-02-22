package com.product.controller;

import com.product.domain.Product;
import com.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/search")
    public List<Product> getProductsByName(@RequestParam String productName){
        return productService.getProductsByName(productName);
    }

    @PostMapping
    public void addProduct(@RequestBody Product product){
        productService.addProduct(product);
    }

    @PutMapping("/{productCode}")
    public void updateProduct(@PathVariable String productCode, @RequestBody Product product){
        product.setProductCode(productCode);
        productService.updateProduct(product);
    }

    @DeleteMapping("/{productCode}")
    public void deleteProduct(@PathVariable String productCode){
        productService.deleteProduct(productCode);
    }

}
