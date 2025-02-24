package com.product.service;

import com.product.domain.Product;

import java.util.List;

public interface ProductService {
    List<Product> getAllProducts();
    List<Product> getProductsByName(String productName);
    void addProduct(Product product);
    void updateProduct(Product product);
    Product getProductsByCode(String productCode);
    void deleteProduct(String productCode);
}
