<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

 
<div class="container">
<br/>
<br/>
<c:if test="${user != null }">
<form action="<c:url value='/reviewInsert.do'/>" method="post" id="review" name="review">
	<input type="hidden" id="writer" name="writer" value="${etc.login_id }"/>
	<input type="hidden" id="id" name="id" value="${etc.id }"/>
	<textarea class="form-control" id="content" name="content"  
		    		  required
		    		  placeholder="최대 100자"></textarea>  
</form>
<br/>
<button class="btn btn-primary" onclick="check_date()">작성하기</button>
</c:if>
<br/>
<ul class="list-group" style="margin-top:30px;"> 
	<c:forEach items="${reviews}" var="row"> 
	<li class="list-group-item" style="padding:5px; font-size:1em;">
		<div>
			${row.content}<br/>
			<small class="text-muted">${row.writer}</small>
		</div>
	</li> 
	</c:forEach>
</ul>
<br/>
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


 <script>
 	function check_date(){
 		var f = document.review  
 		var descript = f.content; 
 		if(content.value.length>100){
 			console.log(descript.value.length);
 			alert("100자까지만 입력 가능합니다.");
 			descript.focus();
 			
 		}else{
 			f.submit();
 		}
 		
 	}
 </script> 