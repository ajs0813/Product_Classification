package com.product.service;

import com.product.dao.ProductMapper;
import com.product.domain.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductMapper productMapper;

    @Override
    public List<Product> getAllProducts(){
        return productMapper.selectAllProduct();
    }

    @Override
    public List<Product> getProductsByName(String productName){
        return productMapper.selectProductsByName(productName);
    }

    @Override
    public void addProduct(Product product){
        productMapper.insertProduct(product);
    }

    @Override
    public void updateProduct(Product product){
        productMapper.updateProduct(product);
    }

    @Override
    public void deleteProduct(String productCode){
        productMapper.deleteProduct(productCode);
    }

}
