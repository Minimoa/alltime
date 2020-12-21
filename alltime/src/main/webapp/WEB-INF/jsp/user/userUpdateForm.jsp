<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 
	<br/>
	<br/>
	<br/>
<c:if test="${user == null }">
	<script>
		toLoginForm();
	</script>
</c:if>
<div class="container"> 
	<h1 class="display-3">회원 수정</h1>       
	<form method="post" action="<c:url value='/admin/userUpdate.do'/>"> 
	    <label for="admin"><h5>admin</h5></label>&nbsp;&nbsp; 
	    <c:choose>
	    	<c:when test="${map.admin eq true }">
	    		<input type="checkbox" value="admin" name="admin"  value="${map.admin}" checked> 
	    	</c:when>
	    	<c:otherwise>
	    		<input type="checkbox" value="admin" name="admin"  value="${map.admin}"> 
	    	</c:otherwise>
	    </c:choose>
	  <div class="form-group">
	    <label for="login_pw2"><h5>Name</h5></label>
	    <input type="text" class="form-control" id="name" name="name" required value="${map.name}" required>
	  </div>  
	  <div class="form-group">
	    <label for="login_id"><h5>Email</h5></label>
	      <c:choose>
	    	<c:when test="${map.admin eq true }">
	    		<input type="text" class="form-control" id="login_id" name ="login_id" required value="${map.login_id}" required> 
	    	</c:when>
	    	<c:otherwise>
	    		<input type="email" class="form-control" id="login_id" name ="login_id" required value="${map.login_id}" required> 
	    	</c:otherwise>
	    </c:choose>
	  </div>
	  <div class="form-group">
	    <label for="login_pw"><h5>Password</h5></label>
	    <input type="password" class="form-control" id="login_pw" name="login_pw" value="${map.login_pw}" required>
	  </div>   
	  <div class="form-group">
	    <label for="tel"><h5>Tel</h5></label>
	    <input type="tel" class="form-control" id="tel" name="tel" value="${map.tel}" required>
	  </div>
	   	<input type="hidden" value="signup" name="type" id="type">
	<button  class="btn btn-primary" onclick="signUpfunc()">수정</button><br/>
	</form>   
</div>
 