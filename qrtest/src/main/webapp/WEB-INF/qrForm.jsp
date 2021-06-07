<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script>
	$(document).ready(function(){
		var checkNum = RegExp(/[^0-9]$/);
		var checkChar = RegExp(/[^a-zA-Zㄱ-ㅎ가-힣]$/);
		$('#btn').click(function(){
			if($('#name').val()==''){
				alert('이름을 입력하세요.');
			} else if(checkChar.test($('#name').val())) {
				alert('이름은 문자만 입력하세요.');
			} else if($('#gender').val()=='') {
				alert('성별을 선택하세요.');
			} else if($('#age').val()==''){
				alert('나이를 입력하세요.');
			} else if(checkNum.test($('#age').val())){
				alert('나이는 숫자만 입력하세요.');
			} else if($('#phone').val()==''){
				alert('전화번호를 입력하세요.'); 
			} else if(checkNum.test($('#phone').val())){
				alert('전화번호는 숫자만 입력하세요.');
			} else {
				$('#submit').submit();
			}
		});
		
	});
</script>
</head>
<body>
<form action="${pageContext.request.contextPath}/qrForm" method="post" id="submit">
	<table>
		<thead>
			<tr>
				<th>이름</th>
				<th>성별</th>
				<th>나이</th>
				<th>폰번호</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td><input class="text" name="name" id="name" placeholder="이름을 입력해주세요."></td>
				<td>
				<select name="gender" id="gender">
					<option value="">==성별==</option>
					<option value="F">여성</option>
					<option value="M">남성</option>
					<option value="N">이외</option>
				</select>
				</td>
				<td><input class="text" name="age" id="age" placeholder="나이를 입력해주세요."></td>
				<td><input class="text" name="phone" id="phone" placeholder="'-'를 제외한 전화번호를 입력해주세요."></td>
			</tr>
		</tbody>
	</table>
	<button type="button" id="btn">제출</button>
	</form>
</body>
</html>