<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>제품 목록</title>
</head>
<link rel="stylesheet" type="text/css" href="/css/productList.css">
<body>
<h1>제품 목록</h1>

<!-- 제품명 검색바-->
<div class="search-container">
    <form id="search-form" action="/products/list" method="get">
        <input type="text" name="productName" placeholder="제품명을 입력하세요" value="${param.productName}"/>
        <button type="submit">검색</button>
    </form>
</div>

<!-- 제품 리스트 -->
<table class="productList">
    <tr>
        <th>제품 코드</th>
        <th>제품명</th>
        <th>분류코드</th>
        <th>분류</th>
        <th>생산일자</th>
        <th>단가</th>
        <th>운영 여부</th>
        <th>생산지 주소</th>
        <th>제품설명</th>
        <th>제품이미지</th>
        <th>등록일자</th>
        <th>수정일자</th>
    </tr>
    <c:forEach var="product" items="${productList}">
        <tr>
            <td><a href="javascript:void(0);"
                   onclick="openUpdateProductWindow('${product.productCode}')">${product.productCode}</a></td>
            <td><a href="javascript:void(0);"
                   onclick="openUpdateProductWindow('${product.productCode}')">${product.productName}</a></td>
            <td>${product.categoryCode}</td>
            <td>${product.categoryName}</td>
            <td><fmt:formatDate value="${product.productDate}" pattern="yyyy-MM-dd"/></td>
            <td><fmt:formatNumber value="${product.unitPrice}" pattern="#,###"/>원</td>
            <td>${product.operationYn}</td>
            <td>${product.manufactureAddress}</td>
            <td>${product.productionDescription}</td>
            <td>
                <c:if test="${not empty product.productImage}">
                    <img src="/${product.productImage}" alt="제품 이미지" width="100"/>
                </c:if>
            </td>
            <td><fmt:formatDate value="${product.createdAt}" pattern="yyyy-MM-dd HH:mm"/></td>
            <td><fmt:formatDate value="${product.updatedAt}" pattern="yyyy-MM-dd HH:mm"/></td>
        </tr>
    </c:forEach>
</table>

<!-- 제품 등록 버튼 -->
<button onclick="openAddProductWindow()" class="product-register-btn">제품 등록</button>
</body>

<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script>
    // 제품 등록 창 열기
    function openAddProductWindow() {
        window.open('/products/add', '제품 등록', 'width=800,height=600');
    }

    // 제품 수정 창 열기
    function openUpdateProductWindow(productCode) {
        window.open('/products/update/' + productCode, '제품 수정', 'width=800,height=600');
    }
</script>
</html>