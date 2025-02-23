package com.product.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Product {
    private String productCode; // 제품코드(PK)
    private String productName; // 제품명
    private String categoryCode; // 제품 분류 코드(FK)
    private String categoryName; // 제품 분류명 (JOIN 용도)
    private String productDate; // 제품 생산일자
    private int unitPrice; // 제품 단가
    private String operationYn; // 운영 여부(Y/N)
    private String manufactureAddress; // 생산지 주소
    private String productionDescription; // 제품 설명
    private String productImage; // 제품 이미지
    private String createdAt; // 시스템 등록일
    private String updatedAt; // 시스템 수정일자
}
