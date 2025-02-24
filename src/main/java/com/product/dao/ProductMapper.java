package com.product.dao;

import com.product.domain.Product;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProductMapper {

    // 1. 모든 제품을 조회하는 메서드
    List<Product> selectAllProduct();

    // 2. 제품명을 기준으로 제품을 검색하는 메서드
    List<Product> selectProductsByName(String productName);

    // 3. 새로운 제품을 추가하는 메서드
    void insertProduct(Product product);

    // 4. 기존 제품 분류를 수정하는 메서드
    void updateProduct(Product product);

    // 5. 특정 제품을 삭제하는 메서드
    void deleteProduct(String productCode);

    Product selectProductByCode(String productCode);
}
