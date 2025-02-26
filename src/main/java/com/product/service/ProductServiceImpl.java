package com.product.service;

import com.product.dao.ProductCategoryMapper;
import com.product.dao.ProductMapper;
import com.product.domain.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductMapper productMapper;

    // 제품 목록 서비스 구현
    @Override
    public List<Product> getAllProducts(){
        return productMapper.selectAllProduct();
    }

    // 제품명 검색 서비스 구현
    @Override
    public List<Product> getProductsByName(String productName){
        return productMapper.selectProductsByName(productName);
    }

    // 제품 등록 서비스 구현
    @Override
    public void addProduct(Product product){
        productMapper.insertProduct(product);
    }

    // 제품 수정 화면 서비스 구현
    @Override
    public Product getProductsByCode(String productCode) {
        return productMapper.selectProductByCode(productCode);
    }

    // 제품 수정 서비스 구현
    @Override
    public void updateProduct(Product product){
        productMapper.updateProduct(product);
    }

    // 제품 삭제 서비스 구현
    @Override
    public void deleteProduct(String productCode){
        productMapper.deleteProduct(productCode);
    }

}
