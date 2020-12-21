<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<style> 
	 
	.bd-placeholder-img {
	  font-size: 1.125rem;
	  text-anchor: middle;
	  -webkit-user-select: none;
	  -moz-user-select: none;
	  -ms-user-select: none;
	  user-select: none;
	}
	
	@media (min-width: 768px) {
	  .bd-placeholder-img-lg {
	    font-size: 3.5rem;
	  }
	} 
  	
  	.card-whole,.Page{
  		display:none;
  	}
	.card-descript{ 
		height: 55px; 
	} 
	.card h3 {
		font-size:1.2em;
	}
	.card-img-top {
		width: auto;
		height: auto;
		max-width: 100%;
		max-height:350px;
	}
	.write-btn{
		float:right;
		margin-right:5px; 
		margin-bottom:10px; 
		background:#fff;
		border:1px solid #ddd;
		color:#333;
	}
	.album>.row {
		background:#fff;
	}
	.row {
		clear:right;
	}
	.container{
		clear:rigt; 
	}
	.album{
		background:#fff;
	} 
	.avail{
		color:green; 
	}
	.disable{
		color:red; 
	}
	 
</style>
<div>
 
 
<main role="main">   
 
 
<div class="album py-5">
    <div class="container">
    <c:if test="${user.admin eq true }">
  	  <a href="<c:url value='/admin/exhibitWrite.do' />" class="btn btn-secondary write-btn ">추가하기</a>
    </c:if> 
      <div class="row">
		<c:choose>
			<c:when test="${fn:length(list) > 0}">
				<c:forEach items="${list }" var="row"> 
					 <div class="card-whole col-md-4">
			          <div class="card mb-4 shadow-sm">
			           <img class="bd-placeholder-img card-img-top" src="<c:url value='${row.image}'/>">
			            <div class="card-body" >
			            	<c:choose>
								<c:when test="${row.AVAILABLE eq true}"> 
									<span class="badge badge-success badge-pill">진행중</span> 
								</c:when>
								<c:otherwise>
									<span class="badge badge-danger badge-pill">종료</span> 
								</c:otherwise>
							</c:choose> 
			            	<h3>${row.title}</h3> 
				            <p class="card-descript card-text">${row.descript}</p>
				              <div class="d-flex justify-content-between align-items-center">
				                <div class="btn-group">
				                  <a type="button" class="btn btn-sm btn-outline-secondary"
				                  		  href="<c:url value='/detailExhibit.do?id=${row.id}'/>"
				                  		  >View</a>
				                  
    							  <c:if test="${user.admin eq true }">		  
					                  <a type="button"  class="btn btn-sm btn-outline-secondary" 
					                     href="<c:url value='/admin/exhibitUpdate.do?id=${row.id}'/>"
					                     style="border-left:0px;border-right:0px;">Edit</a>
					                  <a type="button" class="btn btn-sm btn-outline-secondary"
					                  	 href="<c:url value='/admin/deleteExhibit.do?id=${row.id}'/>">Delete</a>
					              </c:if>
				                </div>
				                <small class="text-muted">${row.start_date} ~ ${row.end_date}</small>
				              </div>
			            </div> <!-- card-body -->
			          </div> <!-- card -->
			        </div> <!-- col-md-4 -->
				</c:forEach>
			</c:when>
			
			<c:otherwise> 
				<div style="height:350px;padding:15px;text-align:center;">조회된 결과가 없습니다.</div> 
			</c:otherwise>
		</c:choose>
			</div><!-- row -->
			
			<c:if test="${pagination.total > 0}"> 
			<c:set var="total" value= "${ pagination.total }" />   
			<c:set var="now" value= "${ pagination.page }" />   
			<nav aria-label="Page navigation example" class="Page">
				  <ul class="pagination">
				    <li class="page-item">
				      <c:if test="${pagination.page >0 }">
				      	<a class="page-link text-dark" href="<c:url value='/exhibitList.do?page=${pagination.page-1}'/>" aria-label="Previous">
				      		<span aria-hidden="true">&laquo;</span>
				      	</a>
				      </c:if>
				        
				    </li>
				    <%	
				    	int i=0;
				    	int total = Integer.parseInt(pageContext.getAttribute("total").toString());  
				    	int now = Integer.parseInt(pageContext.getAttribute("now").toString()); 
				    	
				    	for(;i<Math.ceil(total/6.0);i++){
				    		pageContext.setAttribute("page", i) ;
				    		if(i == now){
				    %>	  
				    	<li class="page-item"><a class="page-link  text-primary" href="<c:url value='/exhibitList.do?page=${page}'/>">${page+1}</a></li> 
				    <%}else{%>
				    	<li class="page-item"><a class="page-link text-dark" href="<c:url value='/exhibitList.do?page=${page}'/>">${page+1}</a></li> 
				    <%}}%>
				    
				    <c:if test="${pagination.page+1<Math.ceil(total/6.0) }">
					    <li class="page-item">
					      <a class="page-link text-dark" href="<c:url value='/exhibitList.do?page=${pagination.page+1}'/>" aria-label="Next">
					        <span aria-hidden="true">&raquo;</span>
					      </a>
					    </li>
					</c:if>
				  </ul> 
				</nav>
			</c:if>
		</div><!-- container -->
		
		
	</div><!-- album -->
</main>

 </div>  
	 
 <script src="//code.jquery.com/jquery-3.3.1.min.js"></script>	
 <script>
 	//ellipsis
 	window.onload = ()=>{
 		var descripts = document.getElementsByClassName("card-descript");
 		var length = 30;
 		for(var i=0;i<descripts.length;i++){
 			if(descripts[i].innerText.length > length){
 				descripts[i].innerText = descripts[i].innerText.substr(0,length)+"  ···";
 			}
 		} 
 		$(".card-whole").fadeIn('slow',function(){
 	 		$(".Page").fadeIn(700);
 		});
 		
 	};
  
  
 </script>