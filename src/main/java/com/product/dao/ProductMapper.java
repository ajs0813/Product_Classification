package com.product.dao;

import com.product.domain.Product;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProductMapper {

    // 제품 목록 메서드
    List<Product> selectAllProduct();

    // 제품명 검색 메서드
    List<Product> selectProductsByName(String productName);

    // 제품 등록 메서드
    void insertProduct(Product product);

    // 제품 수정 화면 메서드
    Product selectProductByCode(String productCode);

    // 제품 수정 메서드
    void updateProduct(Product product);

    // 제품 삭제 메서드
    void deleteProduct(String productCode);
}
