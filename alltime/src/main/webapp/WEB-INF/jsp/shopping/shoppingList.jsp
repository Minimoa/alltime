<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<c:set var="now" value="<%=new java.util.Date()%>" />
<c:if test="${user == null }">
	<script>
		toLoginForm();
	</script>
</c:if>
<style>
	td,th{
		text-align:center;
	}
</style>    
<br/>
<br/> 
<div class="container">
<h2>예매 내역</h2>
<table class="table">
  <thead>
    <tr>
    <th scope="col">예매번호</th>
	<th scope="col">예매정보</th>
	<th scope="col">결제일자</th> 
	<th scope="col"></th> 
    </tr>
  </thead>
  <tbody>
  <c:choose>
	<c:when test="${fn:length(list) > 0}">
		<c:forEach items="${list }" var="row"> 
			<tr>
				<th scope="row">${row.no }</td>
				<td style="width:60%;">
					<div class="row"> 
				  	<!-- ImageView -->
				    <div class="col-2"> 
				    	<img width="100%" class="mx-auto" src="<c:url value='${row.image}'/>">
				    </div>
				  	<!-- InfoView -->
				    <div class="col-8">
					    <c:choose>
					    <c:when test="${row.state eq true}">  
					       <p class="text-muted" style="text-align:left">
					       		제목: ${row.title }<br/>
					       		예매일자: ${row.ex_date }<br/>
					       		구매가격: ${row.price }<br/>
					       </p>
						</c:when>
						<c:otherwise>
					       <s class="text-muted" style="display:block;text-align:left">
					       		제목: ${row.title }<br/>
					       		예매일자: ${row.ex_date }<br/>
					       		구매가격: ${row.price }<br/>
					       </s>
						</c:otherwise> 
						</c:choose>
				    </div>
				    </div>
				</td>  
				<td>
					<p class="text-muted" style="text-align:left">
					결제 일자: ${row.reg_date }<br/>
					취소 일자: ${row.update_date }
					</p>
				</td>  
				<c:choose>
					<c:when test="${row.state eq true}"> 
						<td>
							<br/>
							<fmt:formatDate value="${now}" var="now_date" pattern="yyyy-MM-dd" />
							<fmt:parseDate var="now_dateTime" value="${now_date}" pattern="yyyy-MM-dd" />
							<fmt:parseDate var="ex_dateTime" value="${row.ex_date}" pattern="yyyy-MM-dd" />
							<fmt:parseNumber value="${now_dateTime.time / (1000*60*60*24)}" integerOnly="true" var="now_dateTime"></fmt:parseNumber>
							<fmt:parseNumber value="${ex_dateTime.time / (1000*60*60*24)}" integerOnly="true" var="ex_dateTime"></fmt:parseNumber>
							<c:choose>
								<c:when test="${(ex_dateTime - now_dateTime) >0}">
									<a href="<c:url value='/shoppingModify.do?no=${row.no }'/>">취소</a>
								</c:when>
								<c:otherwise>
									<p>완료</p>
								</c:otherwise>
							</c:choose> 
						</td>
					</c:when>
					<c:otherwise>
						<td>
							<br/>
							<p>취소 완료</p>
						</td>
					</c:otherwise>
				</c:choose> 
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
 
 </div>