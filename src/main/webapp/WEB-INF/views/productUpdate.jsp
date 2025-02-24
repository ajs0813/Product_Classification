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
        <option value="Y" <c:if test="${product.operationYn == 'Y'}">selected</c:if>>운영</option>
        <option value="N" <c:if test="${product.operationYn == 'N'}">selected</c:if>>운영 안함</option>
    </select>

    <label for="manufactureAddress">생산지 주소</label>
    <input type="text" id="manufactureAddress" name="manufactureAddress" value="${product.manufactureAddress}">

    <label for="productionDescription">제품 설명</label>
    <textarea id="productionDescription" name="productionDescription">${product.productionDescription}</textarea>

    <label for="productImage">제품 이미지</label>
    <img src="/images/${product.productImage}" alt="현재 이미지" width="100"/>
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
            // 폼 데이터 생성
            const formData = new FormData($("#productForm")[0]);

            // AJAX로 폼 제출
            $.ajax({
                url: "/products/update", // 폼 제출 URL
                type: "POST",
                data: formData,
                processData: false, // FormData 처리 방지
                contentType: false, // Content-Type 설정 방지
                success: function (response) {
                    // 서버 응답 성공 시
                    window.opener.location.reload(); // 부모 페이지 새로고침
                    window.close(); // 현재 창 닫기
                },
                error: function (xhr, status, error) {
                    // 서버 응답 실패 시
                    alert("제품 수정에 실패했습니다. 다시 시도해주세요.");
                    console.error(error);
                }
            });
        });

        // 삭제 버튼 클릭 이벤트
        $("#deleteBtn").click(function () {
            if (confirm("정말로 이 제품을 삭제하시겠습니까?")) {
                const productCode = $("#productCode").val(); // 제품 코드 가져오기

                // AJAX로 삭제 요청
                $.ajax({
                    url: "/products/delete/" + productCode, // 삭제 요청 URL
                    type: "DELETE",
                    success: function (response) {
                        // 서버 응답 성공 시
                        window.opener.location.reload(); // 부모 페이지 새로고침
                        window.close(); // 현재 창 닫기
                    },
                    error: function (xhr, status, error) {
                        // 서버 응답 실패 시
                        alert("제품 삭제에 실패했습니다. 다시 시도해주세요.");
                        console.error(error);
                    }
                });
            }
        });
    });
</script>
</html>