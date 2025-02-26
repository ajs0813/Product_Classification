package com.product.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class ProductCategory {
    private String categoryCode; // 제품 분류 코드(PK)
    private String categoryName; // 제품 분류명
    private String deleteYn; // 삭제 여부 (Y/N)
    private Date createdAt; // 시스템 등록일
    private Date updatedAt; // 시스템 수정일자
}
