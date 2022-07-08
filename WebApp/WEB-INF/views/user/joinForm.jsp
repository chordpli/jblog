<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JBlog</title>
<!-- css -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/jblog.css">

<!-- js -->
<script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/jquery/jquery-1.12.4.js"></script>

</head>
<body>
	<div id="center-content">
		
		<!-- 메인 해더 -->
		<c:import url="/WEB-INF/views/includes/main-header.jsp"></c:import>

		<div>		
			<form id="joinForm" method="post" action="${pageContext.request.contextPath}/user/join">
				<input type="hidden" id = "idChkBoolean" name = "idBoolean" value = "0"> 
				<table>
			      	<colgroup>
						<col style="width: 100px;">
						<col style="width: 170px;">
						<col style="">
					</colgroup>
		      		<tr>
		      			<td><label for="txtId">아이디</label></td>
		      			<td><input id="txtId" type="text" name="id"></td>
		      			<td><button id="btnIdCheck" type="button">아이디체크</button></td>
		      		</tr>
		      		<tr>
		      			<td></td>
		      			<td id="tdMsg" colspan="2"></td>
		      		</tr> 
		      		<tr>
		      			<td><label for="txtPassword">패스워드</label> </td>
		      			<td><input id="txtPassword" type="password" name="password"  value=""></td>   
		      			<td></td>  			
		      		</tr> 
		      		<tr>
		      			<td><label for="txtUserName">이름</label> </td>
		      			<td><input id="txtUserName" type="text" name="userName"  value=""></td>   
		      			<td></td>  			
		      		</tr>  
		      		<tr>
		      			<td><span>약관동의</span> </td>
		      			<td colspan="3">
		      				<input id="chkAgree" type="checkbox" name="agree" value="y">
		      				<label for="chkAgree">서비스 약관에 동의합니다.</label>
		      			</td>   
		      		</tr>   		
		      	</table>
	      		<div id="btnArea">
					<button id="btnJoin" class="btn" type="submit" >회원가입</button>
				</div>
	      		
			</form>
			
		</div>
		
		<c:import url="/WEB-INF/views/includes/main-footer.jsp"></c:import>
		<!-- 메인 푸터  자리-->
		
	</div>

</body>
<script type="text/javascript">

var idChk;

$("#btnIdCheck").on("click", function(){
	console.log("아이디 체크");
	
	var id = $('[name = "id"]').val();
	
	console.log(id);
	
 	$.ajax({
		url : "${pageContext.request.contextPath }/user/idCheck",		
		type : "post",
		contentType : "application/json",
		data : JSON.stringify(id),
		dataType : "json",
		success : function(result){
			console.log(result);
			
			if(result == "success"){
				$("#tdMsg").text("사용할 수 있는 아이디 입니다.");
				idChk = id;
			}else {
				$("#tdMsg").text("다른 아이디를 입력해주세요.");
			}
		},
		error : function(XHR, status, error) {
			console.error(status + " : " + error);
		}
	}); 
});

$("#btnJoin").on("click", function(){
	console.log("회원가입 버튼 체크");
	
	var id = $('#joinForm [name = id]').val();
	var Check = $('#chkAgree').is(":checked");
	var password = $('#joinForm [name = password]').val();
	var name = $('#joinForm [name = userName]').val();
	
	console.log(id);
	console.log(password);
	console.log(name);
	console.log(Check);
		
	if(id=="" || id == null){
		alert("아이디를 입력해주세요.");
		return false;
	}
	
	console.log(idChk);
	
	if(idChk != id){
		alert("id 중복 확인을 다시 해주세요");
		return false;
	}
	
	if(password =="" || password == null){
		alert("패스워드를 입력해주세요.");
		return false;
	}
	
	if(name == "" || name == null){
		alert("이름을 입력해주세요.");
		return false;
	}
	
	if(Check == false){
		alert("약관에 동의해주세요");
		return false;
	}
	
	return true;
	
});

</script>


</html>