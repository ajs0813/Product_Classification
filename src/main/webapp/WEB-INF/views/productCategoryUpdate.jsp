<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>카테고리 수정</title>
</head>
<link rel="stylesheet" type="text/css" href="/css/productCategoryAdd.css">
<body>
<h2>카테고리 수정</h2>
<form id="categoryForm" action="/categories/update" method="post">
    <input type="hidden" id="categoryCode" name="categoryCode" value="${category.categoryCode}">

    <label for="categoryName">카테고리명</label>
    <input type="text" id="categoryName" name="categoryName" value="${category.categoryName}" required>

    <label for="deleteYn">삭제 여부</label>
    <select id="deleteYn" name="deleteYn" required>
        <option value="Y" <c:if test="${category.deleteYn == 'Y'}">selected</c:if>>Y</option>
        <option value="N" <c:if test="${category.deleteYn == 'N'}">selected</c:if>>N</option>
    </select>

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
            const formData = $("#categoryForm").serialize();

            // AJAX로 폼 제출
            $.ajax({
                url: "/categories/update", // 폼 제출 URL
                type: "POST",
                data: formData,
                success: function (response) {
                    // 서버 응답 성공 시
                    window.opener.location.reload(); // 부모 페이지 새로고침
                    window.close(); // 현재 창 닫기
                },
                error: function (xhr, status, error) {
                    // 서버 응답 실패 시
                    alert("카테고리 수정에 실패했습니다. 다시 시도해주세요.");
                    console.error(error);
                }
            });
        });

        // 삭제 버튼 클릭 이벤트
        $("#deleteBtn").click(function () {
            if (confirm("정말로 이 카테고리를 삭제하시겠습니까?")) {
                const categoryCode = $("#categoryCode").val(); // 카테고리 코드 가져오기

                // AJAX로 삭제 요청
                $.ajax({
                    url: "/categories/delete/" + categoryCode, // 삭제 요청 URL
                    type: "DELETE",
                    success: function (response) {
                        // 서버 응답 성공 시
                        window.opener.location.reload(); // 부모 페이지 새로고침
                        window.close(); // 현재 창 닫기
                    },
                    error: function (xhr, status, error) {
                        // 서버 응답 실패 시
                        alert("카테고리 삭제에 실패했습니다. 다시 시도해주세요.");
                        console.error(error);
                    }
                });
            }
        });
    });
</script>
</html>
