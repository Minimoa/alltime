<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<c:if test="${user == null }">
	<script>
		alert("로그인 후 이용해주세요");
		location.href="<c:url value='/loginForm.do'/>";
	</script>
</c:if>
<c:if test="${user.admin != true }">
	<script>
		alert("잘못된 접근입니다.");
		location.href="<c:url value='/exhibitList.do'/>";
	</script>
</c:if>    
<br/>
<br/>
<br/>
<div class="container">

	<h2>회원 목록</h2>
	<table class="table table-hover">
	  <thead>
	    <tr>
	    <th scope="col">번호</th>
		<th scope="col">아이디</th>
		<th scope="col">이름</th>
		<th scope="col">전화번호</th>
		<th scope="col">관리자</th>
		<th scope="col">수정</th>
		<th scope="col">삭제</th> 
	    </tr>
	  </thead>
	  <tbody>
	  <c:choose>
		<c:when test="${fn:length(list) > 0}">
			<c:forEach items="${list }" var="row"> 
				<tr>
					<th scope="row">${row.no }</td>
					<td>${row.login_id }</td>
					<td>${row.name }</td>
					<td>${row.tel }</td>
					<td>${row.admin }</td>
					<td><a href="<c:url value='/admin/userUpdateForm-admin.do?login_id=${row.login_id }'/>">수정</a></td>
					<td><a href="<c:url value='/admin/userDelete.do?login_id=${row.login_id }'/>">삭제</a></td>
				</tr>
			</c:forEach>
		</c:when>
		<c:otherwise>
			<tr>
				<td colspan="4">조회된 결과가 없습니다.</td>
			</tr>
		</c:otherwise>
	</c:choose>
	   
	  </tbody>
	</table>
	<!-- pagination -->
	<c:if test="${pagination.total > 0}"> 
	<c:set var="total" value= "${ pagination.total }" />   
	<c:set var="now" value= "${ pagination.page }" />   
	<nav aria-label="Page navigation" >
		  <ul class="pagination">
		    <li class="page-item">
		      <c:if test="${pagination.page >0 }">
		      	<a class="page-link text-dark" href="<c:url value='/?page=${pagination.page-1}'/>" aria-label="Previous">
		      		<span aria-hidden="true">&laquo;</span>
		      	</a>
		      </c:if>
		        
		    </li>
		    <%	
		    	int i=0;
		    	int total = Integer.parseInt(pageContext.getAttribute("total").toString());  
		    	int now = Integer.parseInt(pageContext.getAttribute("now").toString()); 
		    	
		    	for(;i<Math.ceil(total/15.0);i++){
		    		pageContext.setAttribute("page", i) ;
		    		if(i == now){
		    %>	  
		    	<li class="page-item"><a class="page-link  text-primary" href="<c:url value='/?page=${page}'/>">${page+1}</a></li> 
		    <%}else{%>
		    	<li class="page-item"><a class="page-link text-dark" href="<c:url value='/?page=${page}'/>">${page+1}</a></li> 
		    <%}}%>
		    
		    <c:if test="${pagination.page+1<Math.ceil(total/15.0) }">
			    <li class="page-item">
			      <a class="page-link text-dark" href="<c:url value='/?page=${pagination.page+1}'/>" aria-label="Next">
			        <span aria-hidden="true">&raquo;</span>
			      </a>
			    </li>
			</c:if>
		  </ul> 
		</nav>
	</c:if> 
 </div>