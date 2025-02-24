<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>카테고리 등록</title>
</head>
<link rel="stylesheet" type="text/css" href="/css/productCategoryAdd.css">
<body>
<h2>카테고리 등록</h2>
<form id="categoryForm" action="/categories/add" method="post">
    <label for="categoryCode">카테고리 코드</label>
    <input type="text" id="categoryCode" name="categoryCode" required>

    <label for="categoryName">카테고리명</label>
    <input type="text" id="categoryName" name="categoryName" required>

    <label for="deleteYn">삭제 여부</label>
    <select id="deleteYn" name="deleteYn" required>
        <option value="Y">Y</option>
        <option value="N">N</option>
    </select>

    <button type="button" id="submitBtn">등록</button>
</form>

</body>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script>
    $(document).ready(function () {
        // 등록 버튼 클릭 이벤트
        $("#submitBtn").click(function () {
            // 폼 데이터 생성
            const formData = new FormData($("#categoryForm")[0]);

            // AJAX로 폼 제출
            $.ajax({
                url: "/categories/add",
                type: "POST",
                data: formData,
                processData: false,
                contentType: false,
                success: function (response) {
                    window.opener.location.reload(); // 부모 페이지 새로고침
                    window.close(); // 현재 창 닫기
                },
                error: function (xhr, status, error) {
                    alert("카테고리 등록에 실패했습니다. 다시 시도해주세요.");
                    console.error(error);
                }
            });
        });
    });
</script>
</html>
