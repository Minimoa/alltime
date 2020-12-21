<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%> 
    

<div class="container"> 
	<br/>
	<h1 class="display-1">Sign In</h1>    
	<form method="post" action="<c:url value ='/login.do'/>" name="frm">  
	  <div class="form-group">
	    <label for="login_id"><h3>Email</h3></label>
	    <input type="text" class="form-control" id="login_id" name ="login_id" required>
	  </div>
	  <div class="form-group">
	    <label for="login_pw"><h3>Password</h3></label>
	    <input type="password" class="form-control" id="login_pw" name="login_pw" required>
	  </div>  
	  <input type="hidden" value="signin" name="type" id="type">
	  <button type="submit" class="btn btn-primary">LOGIN</button>
	</form> 
	     <a href="<c:url value='/signUpForm.do'/>">회원이 아니세요? 회원가입</a>
</div>

