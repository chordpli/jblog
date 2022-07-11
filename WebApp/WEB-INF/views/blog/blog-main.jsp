<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%> 

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JBlog</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/jblog.css">
<script type="text/javascript" src="${pageContext.request.contextPath }/assets/js/jquery/jquery-1.12.4.js"></script>
</head>

<body>
	<div id="wrap">

		<!-- 개인블로그 해더 -->
		<c:import url="/WEB-INF/views/includes/blog-header.jsp"></c:import>
		
		<div id="content" class="clearfix">
			<div id="profilecate_area">
				<div id="profile">
					
					<!-- 기본이미지 -->
					<img id="proImg" src="${pageContext.request.contextPath}/upload/${blog.blogOneData.logoFile}">
					
					<!-- 사용자업로드 이미지 -->
					<%-- <img id="proImg" src=""> --%>
					
					<div id="nick">${blog.blogOneData.userName }</div>
				</div>
				<div id="cate">
					<div class="text-left">
						<strong>카테고리</strong>
					</div>
					<ul id="cateList" class="text-left">
						<c:forEach items="${blog.cateList}" var = "cVo">
							<li><a href="${pageContext.request.contextPath}/${id }/${cVo.cateNo }"> ${cVo.cateName } </a></li>
						</c:forEach>
						
					</ul>
				</div>
			</div>
			<!-- profilecate_area -->
			
			<div id="post_area">
				
				<c:choose>
					<c:when test="${blog.posting != null }">
						<div id="postBox" class="clearfix">
								<div id="postTitle" class="text-left"><strong>${blog.posting.postTitle}</strong></div>
								<div id="postDate" class="text-left"><strong>${blog.posting.postRegDate}</strong></div>
								<div id="postNick">${blog.blogOneData.userName }님</div>
						</div>
						<!-- //postBox -->
					
						<div id="post" >
							${blog.posting.postContent }
						</div>
						<!-- //post -->
					</c:when>
					<c:otherwise>
						<!-- 글이 없는 경우 -->
						<div id="postBox" class="clearfix">
									<div id="postTitle" class="text-left"><strong>등록된 글이 없습니다.</strong></div>
									<div id="postDate" class="text-left"><strong></strong></div>
									<div id="postNick"></div>
						</div>
					    
						<div id="post" >
						</div>
					</c:otherwise>
				</c:choose>
				
				<!-- reply area -->
				<div id = "reply_area">
					<div id = "postReplyArea">
						<table>
							<c:if test="${authUser != null }">
								<tr>
									<td id = "postReplyID">${loginId}</td>
									<td><input type="text" id="replyValue" value=""></td>
									<td><button type="button" id="postReply">저장</button></td>
								</tr>
							</c:if>
						</table>
					</div>
					
					<div id = "replyList">
						<table>
							<tr>
								<td id = "replyWriter">코멘트 작성자</td>
								<td id = "replyContent">내용</td>
								<td id = "replyDate">날짜</td>
								<td id = "btnDelReply">삭제버튼</td>
							</tr>
							<tr>
								<td id = "replyWriter">코멘트 작성자</td>
								<td id = "replyContent">내용</td>
								<td id = "replyDate">날짜</td>
								<td id = "btnDelReply">삭제버튼</td>
							</tr>
							<tr>
								<td id = "replyWriter">코멘트 작성자</td>
								<td id = "replyContent">내용</td>
								<td id = "replyDate">날짜</td>
								<td id = "btnDelReply">삭제버튼</td>
							</tr>
						</table>
					</div>
				
				</div>
						
				<div id="list">
					<div id="listTitle" class="text-left"><strong>카테고리의 글</strong></div>
					<table>
						<colgroup>
							<col style="">
							<col style="width: 20%;">
						</colgroup>
						
						<c:forEach items="${blog.postList}" var = "post">
							<tr>
								<c:choose>
									<c:when test="${cateNo != null }">
										<td class="text-left"><a href="${pageContext.request.contextPath}/${id }/${cateNo }/${post.postNo }">${post.postTitle }</a></td>	
									</c:when>
									<c:otherwise>
										<td class="text-left"><a href="${pageContext.request.contextPath}/${id }/${post.cateNo }/${post.postNo }">${post.postTitle }</a></td>
									</c:otherwise>
								</c:choose>
								
								<td class="text-right">${post.postRegDate }</td>
							</tr>
						</c:forEach>
						
					</table>
				</div>
				<!-- //list -->
			</div>
			<!-- //post_area -->
			
			
			
		</div>	
		<!-- //content -->
		<div class=></div>
		<c:import url="/WEB-INF/views/includes/blog-footer.jsp"></c:import>
		
	
	
	</div>
	<!-- //wrap -->
</body>
</html>