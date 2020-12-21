<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<div class="container"> 
	<br/>
	<h1 class="display-1">Sign Up</h1>       
	<form method="post" action="<c:url value='/signup.do'/>" id="signupForm" name="signupForm">
	  <div class="form-group">
	    <label for="login_pw2"><h5>Name</h5></label>
	    <input type="text" class="form-control" id="name" name="name" required>
	  </div>  
	  <div class="form-group">
	    <label for="login_id"><h5>Email</h5></label>
	    <input type="email" class="form-control" id="login_id" name ="login_id" required>
	  </div>
	  <div class="form-group">
	    <label for="login_pw"><h5>Password</h5></label>
	    <input type="password" class="form-control" id="login_pw" name="login_pw" required>
	  </div>  
	  <div class="form-group">
	    <label for="login_pw2"><h5>Password confirm</h5></label>
	    <input type="password" class="form-control" id="login_pw2" name="login_pw2" required>
	  </div>  
	  <div class="form-group">
	    <label for="tel"><h5>Tel</h5></label>
	    <input type="tel" class="form-control" id="tel" name="tel" required>
	  </div>
	   	<input type="hidden" value="signup" name="type" id="type">
	</form>  
	<button  class="btn btn-primary" onclick="signUpfunc()">SIGNUP</button><br/>
	<a href="?type=signin">이미 회원이세요? 로그인 하기</a>
</div>
<script>

	function email_check( email ) { 
	    var regex=/([\w-\.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([\w-]+\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\]?)$/;
	    return (email != '' && email != 'undefined' && regex.test(email));
	
	}
	
	function signUpfunc(){ 
		var f = document.signupForm;
		console.log(f);
		var inputs = document.getElementsByTagName("input"); 
		var pw1 = inputs[2];
		var pw2 = inputs[3]; 
		
		for(var i=0;i<inputs.length;i++){ 
			if(i==1 && !email_check(inputs[i].value)){ 
				alert("올바른 이메일 형식이 아닙니다.");
				inputs[i].focus();
				return;
			}
			else if(inputs[i].value == ""){
				inputs[i].focus();
				return;
			}
		} 
 
		if(pw1.value == pw2.value){
			f.submit(); 
		}
		else{
			alert("비밀번호와 비밀번호 확인이 일치하지 않습니다."); 
			pw1.focus();
		}
	}
</script>