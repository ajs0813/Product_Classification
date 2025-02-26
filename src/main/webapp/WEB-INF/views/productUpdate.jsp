<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>제품 수정</title>
</head>
<link rel="stylesheet" type="text/css" href="/css/productAdd.css">
<body>
<h2>제품 수정</h2>
<form id="productForm" action="/products/update" method="post" enctype="multipart/form-data">
    <input type="hidden" id="productCode" name="productCode" value="${product.productCode}">

    <label for="productName">제품명</label>
    <input type="text" id="productName" name="productName" value="${product.productName}" required>

    <label for="categoryCode">분류</label>
    <select id="categoryCode" name="categoryCode" required>
        <c:forEach var="category" items="${categoryList}">
            <option value="${category.categoryCode}" <c:if test="${category.categoryCode == product.categoryCode}">selected</c:if>>${category.categoryName}</option>
        </c:forEach>
    </select>

    <label for="productDate">생산일자</label>
    <input type="date" id="productDate" name="productDate" value="${product.productDate}" required>

    <label for="unitPrice">단가</label>
    <input type="number" id="unitPrice" name="unitPrice" value="${product.unitPrice}" required>

    <label for="operationYn">운영 여부</label>
    <select id="operationYn" name="operationYn" required>
        <option value="Y" <c:if test="${product.operationYn == 'Y'}">selected</c:if>>Y</option>
        <option value="N" <c:if test="${product.operationYn == 'N'}">selected</c:if>>N</option>
    </select>

    <label for="manufactureAddress">생산지 주소</label>
    <input type="text" id="manufactureAddress" name="manufactureAddress" value="${product.manufactureAddress}">

    <label for="productionDescription">제품 설명</label>
    <textarea id="productionDescription" name="productionDescription">${product.productionDescription}</textarea>

    <label for="productImage">제품 이미지</label>
    <c:if test="${not empty product.productImage}">
        <img src="/${product.productImage}" alt="제품 이미지" width="100"/>
    </c:if>
    <input type="file" id="productImage" name="productImage" accept="image/*">

    <button type="button" id="submitBtn">수정</button>
    <button type="button" id="deleteBtn">삭제</button>
</form>

</body>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script>
    $(document).ready(function () {
        // 수정 버튼 클릭 이벤트
        $("#submitBtn").click(function () {
            const formData = new FormData($("#productForm")[0]);

            // AJAX로 폼 제출
            $.ajax({
                url: "/products/update",
                type: "POST",
                data: formData,
                processData: false,
                contentType: false,
                success: function (response) {
                    window.opener.location.reload(); // 부모 페이지 새로고침
                    window.close(); // 현재 창 닫기
                },
                error: function (xhr, status, error) {
                    alert("제품 수정에 실패했습니다. 다시 시도해주세요.");
                    console.error(error);
                }
            });
        });

        // 삭제 버튼 클릭 이벤트
        $("#deleteBtn").click(function () {
            if (confirm("정말로 이 제품을 삭제하시겠습니까?")) {
                const productCode = $("#productCode").val();

                // AJAX로 삭제 요청
                $.ajax({
                    url: "/products/delete/" + productCode,
                    type: "DELETE",
                    success: function (response) {
                        window.opener.location.reload(); // 부모 페이지 새로고침
                        window.close(); // 현재 창 닫기
                    },
                    error: function (xhr, status, error) {
                        alert("제품 삭제에 실패했습니다. 다시 시도해주세요.");
                        console.error(error);
                    }
                });
            }
        });
    });
</script>
</html>