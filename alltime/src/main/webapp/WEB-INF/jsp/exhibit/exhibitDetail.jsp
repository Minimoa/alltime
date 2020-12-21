<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<c:set var="now" value="<%=new java.util.Date(new java.util.Date().getTime()+60*60*24*1000)%>" />

<div class="container"> 
<br/>
<br/>  
  <div class="row"> 
  	<!-- ImageView -->
    <div class="col-md-4 order-md-2 mb-4">
      <h4 class="d-flex justify-content-between align-items-center mb-3">프로그램</h4>
    	<img width="100%" height="370px"class="mx-auto"src="<c:url value='${map.image}'/>">
    </div>
    <!-- InfoView -->
    <div class="col-md-4 order-md-2 mb-4">
      <br/>
      <h4 class="d-flex justify-content-between align-items-center mb-3">  </h4>
		<c:choose>
			<c:when test="${map.AVAILABLE eq true}"> 
				<span class="badge badge-success badge-pill">진행중</span> 
			</c:when>
			<c:otherwise>
				<span class="badge badge-danger badge-pill">종료</span> 
			</c:otherwise>
		</c:choose> 
		<h3 id="ex_title">${map.title}</h3> 
		<small class="text-muted">${map.start_date} ~ ${map.end_date}</small>
		<p style="white-space:pre-wrap; font-size:0.9em;">${map.descript}</p>     
    </div>
    
    <!-- ReservationView -->
    	<div class="col-md-4 order-md-2 mb-4">
	      <h4 class="d-flex justify-content-between align-items-center mb-3">
	       	예매
	      </h4>
	       
	      <ul class="list-group mb-3">
        	<c:choose>
    			<c:when test="${map.AVAILABLE eq true}"> 
    				<li class="list-group-item d-flex justify-content-between">  
    					<input id="start_date" value="<fmt:formatDate value="${now}" pattern="yyyy-MM-dd" />" type="hidden"/>
    					<input id="end_date" value="${map.end_date }" type="hidden"/> 
    					 
			          	<span style="padding-top:5px;" >Date</span>   
					 	<input id="date" type="text" placeholder="날짜" readonly
					 		   style="display:block; 
					 		   		  border:0px; 
					 		   		  text-algin:right;
					 		   		  padding-top:-5px;
					 		   		  text-align:right; 
					 		   		  cursor:pointer; 
					 		   		  color:#333;
					 		   		  "
					 	/>
					 	<label for="date" class="text-primary" style="padding-top:5px;" >&gt;</label>
					 </li>
			        <li class="list-group-item d-flex justify-content-between">
			          <span>Total</span>  
			          <c:choose>
				          <c:when test="${map.price eq '0'}">
					          <strong id="ex_price">무료</strong> 
				          </c:when>
				          <c:otherwise>
					          <strong id="ex_price"><fmt:formatNumber value="${map.price }" pattern="#,###" />원</strong>				          	
				          </c:otherwise>
			          </c:choose>
			        </li> 
			 	<form name="buy" id="buy" method="post" action="<c:url value='/shoppingItemInsert.do'/>">
					<input type="hidden" name="ex_date" id="ex_date"/>
					<input type="hidden" name="ex_id" id="ex_id" value="${map.id }"/>
					<input type="hidden" name="login_id" id="login_id" value="${user.login_id }"/>		 	
			 	</form>
	            <button id="buy-btn" class=" btn btn-secondary" style="width:100%;">예매하기</button>   
				</c:when>
				<c:otherwise>
				
	        <li class="list-group-item">
		          <div style="text-align:center;padding:85px; color:#888;"> 
		             <i class="fas fa-calendar-times" style="font-size:8em;margin:auto;display:block;width:80%;"></i><br/>
		             <strong>종료된 전시입니다.</strong>
		          </div> 
		    </li>
				</c:otherwise>	        	
        	</c:choose>  
	        </ul>
	        <div>
		      <div class="d-flex justify-content-between align-items-center mb-3">
		      <h4>한줄소감</h4>
		      <%-- <c:if test="${fn:length(reviews) > 0}"> --%>
			  <a href="<c:url value='/reviewList.do?id=${map.id }'/>" style="float:right;">더보기 &gt;</a>	
		      <%-- </c:if> --%>
		      </div>
		      
		      <ul class="list-group" style="clear:right;">  
				<c:choose>
					<c:when test="${fn:length(reviews) > 0}">
				      	<c:forEach items="${reviews}" var="row"> 
					      	<li class="list-group-item" style="padding:5px; font-size:1em;">
					      		<div>
					      			${row.content}<br/>
					      			<small class="text-muted">${row.writer}</small>
					      		</div>
					      	</li> 
				      	</c:forEach>
				      </c:when>
				      <c:otherwise>
				      		<li class="list-group-item" style="text-align:center; padding:5px; font-size:1em; ">아직 없음</li>
				      </c:otherwise>
			     </c:choose>
		      	
		      </ul>
		    </div>
	    </div>   
</div>   
</div>

 <script>
$(function(){ 
	
	var range = $('#start_date').val()+" - "+$('#end_date').val();
	$('#date').val(''); 
	$('#ex_date').val($("#date").val());
	
	$('#date').daterangepicker({   
		"singleDatePicker": true,
	    "locale": {
	    	format: 'YYYY-MM-DD', 
	    },
	    "alwaysShowCalendars":true,
	    "startDate": $('#start_date').val(),
	  	"endDate": $('#end_date').val(),
	    "minDate": $('#start_date').val(),
	  	"maxDate": $('#end_date').val()
	}, function(start, end, label) {
	  console.log("New date range selected: ' + start.format('YYYY-MM-DD') + ' to ' + end.format('YYYY-MM-DD') + ' (predefined range: ' + label + ')");
	});
	$('#date').on('apply.daterangepicker', function(ev, picker) {  
		  $('#ex_date').val($("#date").val());
	});
	
	$("#buy-btn").on("click",function(){ 
		if($("#login_id").val() == null || $("#login_id").val() == ""){
			toLoginForm();
			return;
		}  
		if($("#ex_date").val() == ''){ 
			$('#ex_date').val($('#start_date').val()); 
		}
		var msg = $("#ex_title").text()+"\n"+
				  $("#ex_date").val()+"\n"+ 
				 ($("#ex_price").text()=="무료"?"0":$("#ex_price").text())+"\n"+
				 "선택하셨습니다.\n예약하시겠습니까?";
		if(confirm(msg)){
			$("#buy").submit();
		}
			
		
	});

});
</script>