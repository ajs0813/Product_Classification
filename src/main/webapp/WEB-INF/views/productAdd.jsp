<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>제품 추가</title>
</head>
<link rel="stylesheet" type="text/css" href="/css/productAdd.css">
<body>
<h2>제품 등록</h2>
<form id="productForm" action="/products/add" method="post" enctype="multipart/form-data">
    <label for="productCode">제품 코드</label>
    <input type="text" id="productCode" name="productCode" placeholder="중복되지 않는 코드를 입력해주세요." required>

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

    <label for="unitPrice">제품 단가</label>
    <input type="number" id="unitPrice" name="unitPrice" required>

    <label for="operationYn">운영 여부</label>
    <select id="operationYn" name="operationYn" required>
        <option value="Y">Y</option>
        <option value="N">N</option>
    </select>

    <label for="manufactureAddress">생산지 주소</label>
    <input type="text" id="manufactureAddress" name="manufactureAddress">

    <label for="productionDescription">제품 설명</label>
    <textarea id="productionDescription" name="productionDescription"></textarea>

    <label for="productImage">제품 이미지</label>
    <input type="file" id="productImage" name="productImage" accept="image/*">

    <button type="button" id="submitBtn">등록</button>
</form>

</body>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script>
    $(document).ready(function () {
        // 등록 버튼 클릭 이벤트
        $("#submitBtn").click(function () {
            // 폼 데이터 생성
            const formData = new FormData($("#productForm")[0]);

            // AJAX로 폼 제출
            $.ajax({
                url: "/products/add",
                type: "POST",
                data: formData,
                processData: false,
                contentType: false,
                success: function (response) {
                    window.opener.location.reload(); // 부모 페이지 새로고침
                    window.close(); // 현재 창 닫기
                },
                error: function (xhr, status, error) {
                    alert("제품 등록에 실패했습니다. 다시 시도해주세요.");
                    console.error(error);
                }
            });
        });
    });
</script>
</html>