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
	<div id="wrap">
		
		<!-- 개인블로그 해더 -->
		<c:import url="/WEB-INF/views/includes/blog-header.jsp"></c:import>

		<div id="content">
			<ul id="admin-menu" class="clearfix">
				<li class="tabbtn"><a href="${pageContext.request.contextPath}/${id}/admin/basic">기본설정</a></li>
				<li class="tabbtn selected"><a href="${pageContext.request.contextPath}/${id}/admin/category">카테고리</a></li>
				<li class="tabbtn"><a href="${pageContext.request.contextPath}/${id}/admin/writeform">글작성</a></li>
			</ul>
			<!-- //admin-menu -->
			
			<div id="admin-content">
			
				<table id="admin-cate-list">
					<colgroup>
						<col style="width: 50px;">
						<col style="width: 200px;">
						<col style="width: 100px;">
						<col>
						<col style="width: 50px;">
					</colgroup>
		      		<thead>
			      		<tr>
			      			<th>번호</th>
			      			<th>카테고리명</th>
			      			<th>포스트 수</th>
			      			<th>설명</th>
			      			<th>삭제</th>      			
			      		</tr>
		      		</thead>
		      		<tbody id="cateList">
		      			<!-- 리스트 영역 -->
		      			
		      			
						<!-- 리스트 영역 -->
					</tbody>
				</table>
      	
		      	<table id="admin-cate-add" >
		      		<colgroup>
						<col style="width: 100px;">
						<col style="">
					</colgroup>
		      		<tr>
		      			<td class="t">카테고리명</td>
		      			<td><input type="text" name="name" value=""></td>
		      		</tr>
		      		<tr>
		      			<td class="t">설명</td>
		      			<td><input type="text" name="desc"></td>
		      		</tr>
		      	</table> 
			
				<div id="btnArea">
		      		<button id="btnAddCate" class="btn_l" type="submit" >카테고리추가</button>
		      	</div>
			
			</div>
			<!-- //admin-content -->
		</div>	
		<!-- //content -->
		
		
		<!-- 개인블로그 푸터 -->
		<c:import url="/WEB-INF/views/includes/blog-footer.jsp"></c:import>
	
	
	</div>
	<!-- //wrap -->
</body>
<script type="text/javascript">
/* 준비가 끝났을 때  */
$(document).ready(function(){
	
	/* 리스트 요청 + 그리기 */
	fetchList()
});

/* 새로고침 */
function refreshMemList(){
	location.reload();
}

/* 리스트 요청 */
function fetchList(){
	
	console.log("fetchList()");
	
	$.ajax({
		
		url : "${pageContext.request.contextPath }/{id}/admin/category/list",		
		type : "post",
		//contentType : "application/json",
		//data : {name: ”홍길동"},
		
		dataType : "json",
		success : function(cList){
			//화면 data + html 그린다
			for(var i=0; i<cList.length; i++){
				render(cList[i]);  //vo --> 화면에 그린다.
			}
		},
		error : function(XHR, status, error) {
			console.error(status + " : " + error);
		}
	});
}


/* 리스트 그리기 1개씩*/
function render(cList){
	console.log("render()");

	var str = '';
	str += '	<tr>' ;
	str += '		<td>'+cList.rn+'</td>' ;
	str += '		<td>'+cList.cateName+'</td>' ;
	str += '		<td>'+cList.postCount+'</td>' ;
	str += '		<td>'+cList.description+'</td>';
	str += '	    <td class="text-center">' ;
	str += '	    	<img class="btnCateDel" data-postcount= "'+cList.postCount+'" data-cateno="'+cList.cateNo+'" src="${pageContext.request.contextPath}/assets/images/delete.jpg">' ;
	str += '	    </td>';
	str += '	</tr>';
	
	$("#cateList").prepend(str);
	
}


/* 추가버튼 클릭할 때 */
$("#btnAddCate").on("click", function(){
	console.log("카테고리 추가 클릭");
	
	// 데이터 모으기
	
	var cateName = $('#admin-cate-add [name = name]').val();
	var description = $('#admin-cate-add [name = desc]').val();
	
	var CategoryVo = {
			cateName: cateName,
			description: description
	};
	
	
	console.log(CategoryVo);
	
 	$.ajax({
		
		url : "${pageContext.request.contextPath }/{id}/admin/category/insertCategory",		
		type : "post",
		contentType : "application/json",
		data : JSON.stringify(CategoryVo),
		dataType : "json",
		success : function(result){
			//성공시 처리해야될 코드 작성
			console.log(result);
			
			//성공이면 지우고
			if(result == "success"){
				console.log("성공");
				$("#cateList").empty();
			 	fetchList();
			 	
			 	$("[name = name]").val("");
			 	$("[name = desc]").val("");

			}else {
				console.log("실패");
			}
			
		},
		error : function(XHR, status, error) {
			console.error(status + " : " + error);
		}
	}); 
	//성공이면 리스트에서 제거하기
	
	//모달창 닫기
	
 	
	
});

/* 삭제버튼 클릭할 때 */
$("#cateList").on("click", ".btnCateDel", function(){
	console.log("카테고리 삭제 클릭");
	var $this = $(this);
	var cateNo = $this.data("cateno");
	var postCount = $this.data("postcount");
	
	console.log(cateNo);
	console.log(postCount);
	
 	if(postCount > 0){
 		alert("삭제할 수 없습니다.");
 	}else{
 		$.ajax({
 			
 			url : "${pageContext.request.contextPath }/{id}/admin/category/deleteCategory",		
 			type : "post",
 			contentType : "application/json",
 			data : JSON.stringify(cateNo),
 			dataType : "json",
 			success : function(result){
 				//성공시 처리해야될 코드 작성
 				console.log(result);
 				
 				//성공이면 지우고
 				if(result == "success"){
 					console.log("성공");
 					$("#cateList").empty();
 				 	fetchList();
 				 	
 				 	
 				}else {
 					console.log("실패");
 				}
 				
 			},
 			error : function(XHR, status, error) {
 				console.error(status + " : " + error);
 			}
 		});
 	}
});


</script>



</html>