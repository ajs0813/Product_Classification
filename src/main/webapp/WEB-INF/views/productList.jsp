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
<div class="search-container">
    <input type="text" name="productName" placeholder="제품명을 입력하세요" />
    <button type="submit">검색</button>
</div>
<table class="productList">
    <tr>
        <th>제품 코드</th>
        <th>제품명</th>
        <th>분류</th>
        <th>생산일자</th>
        <th>단가</th>
        <th>운영 여부</th>
        <th>생산지 주소</th>
        <th>제품설명</th>
        <th>제품이미지</th>
    </tr>
    <c:forEach var="product" items="${productList}">
        <tr>
            <td>${product.productCode}</td>
            <td>${product.productName}</td>
            <td>${product.categoryName}</td>
            <td>${product.productDate}</td>
            <td><fmt:formatNumber value="${product.unitPrice}" pattern="#,###" />원</td>
            <td>${product.operationYn}</td>
            <td>${product.manufactureAddress}</td>
            <td>${product.productionDescription}</td>
            <td><img src="${product.productImage}" alt="제품 이미지" width="100" /></td>
        </tr>
    </c:forEach>
</table>

<!-- 제품 등록 버튼 -->
<a href="javascript:void(0);" class="product-register-btn" onclick="openPopup()">제품 등록</a>

<!-- 팝업창 -->
<div id="popupForm" class="popup">
    <div class="popup-content">
        <span class="close" onclick="closePopup()">&times;</span>
        <h2>제품 등록</h2>
        <form action="/products/add" method="post" enctype="multipart/form-data">
            <label for="productCode">제품 코드</label>
            <input type="text" id="productCode" name="productCode" required>

            <label for="productName">제품명</label>
            <input type="text" id="productName" name="productName" required>

            <label for="categoryCode">분류</label>
            <select id="categoryCode" name="categoryCode" required>
                <c:forEach var="category" items="${categoryList}">
                    <option value="${category.categoryCode}">${category.categoryName}</option>
                </c:forEach>
            </select>

            <label for="productDate">생산일자</label>
            <input type="date" id="productDate" name="productDate" required>

            <label for="unitPrice">단가</label>
            <input type="number" id="unitPrice" name="unitPrice" required>

            <label for="operationYn">운영 여부</label>
            <select id="operationYn" name="operationYn" required>
                <option value="Y">운영</option>
                <option value="N">운영 안함</option>
            </select>

            <label for="manufactureAddress">생산지 주소</label>
            <input type="text" id="manufactureAddress" name="manufactureAddress">

            <label for="productionDescription">제품 설명</label>
            <textarea id="productionDescription" name="productionDescription"></textarea>

            <label for="productImage">제품 이미지</label>
            <input type="file" id="productImage" name="productImage" accept="image/*">

            <button type="submit">등록</button>
        </form>
    </div>
</div>

</body>

<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script>
    // jQuery를 사용한 팝업 열기
    function openPopup() {
        $('#popupForm').show();
    }

    // jQuery를 사용한 팝업 닫기
    function closePopup() {
        $('#popupForm').hide();
    }
</script>
</html>