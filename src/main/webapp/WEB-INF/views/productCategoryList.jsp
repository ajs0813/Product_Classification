<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>카테고리 목록</title>
</head>
<link rel="stylesheet" type="text/css" href="/css/productCategoryList.css">
<body>
<h1>제품 분류 목록</h1>

<!-- 제품 분류명 검색바 -->
<div class="search-container">
    <form id="search-form" action="/categories/list" method="get">
        <input type="text" name="categoryName" placeholder="제품 분류명을 입력하세요" value="${param.categoryName}"/>
        <button type="submit">검색</button>
    </form>
</div>

<!-- 제품 분류 리스트 -->
<table class="categoryList">
    <tr>
        <th>제품분류 코드</th>
        <th>제품분류명</th>
        <th>삭제 여부</th>
    </tr>
    <c:forEach var="category" items="${categoryList}">
        <tr>
            <td><a href="javascript:void(0);" onclick="openUpdateCategoryWindow('${category.categoryCode}')">${category.categoryCode}</a></td>
            <td><a href="javascript:void(0);" onclick="openUpdateCategoryWindow('${category.categoryCode}')">${category.categoryName}</a></td>
            <td>${category.deleteYn}</td>
        </tr>
    </c:forEach>
</table>

<!-- 제품 분류 등록 버튼 -->
<button onclick="openAddCategoryWindow()" class="category-register-btn">제품 분류 등록</button>
</body>

<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script>
    // 제품 분류 등록 창 열기
    function openAddCategoryWindow() {
        window.open('/categories/add', '제품 분류 등록', 'width=600,height=400');
    }

    // 제품 분류 수정 창 열기
    function openUpdateCategoryWindow(categoryCode) {
        window.open('/categories/update/' + categoryCode, '제품 분류 수정', 'width=600,height=400');
    }
</script>
</html>
