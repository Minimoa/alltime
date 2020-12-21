<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

 
 
<c:if test="${status eq '2'}">
	<script>
		alert("회원 가입이 완료되었습니다. 로그인해주세요.");  
		location.href="<c:url value='loginForm.do'/>"; 
	</script>
</c:if> 

<c:if test="${status eq '-1'}">
	<script>
		alert("존재하지 않는 아이디입니다.");
		location.href="<c:url value='loginForm.do'/>"; 
	</script>
</c:if>
<c:if test="${status eq '0'}">
	<script>
		alert("비밀번호가 틀립니다.");
		location.href="<c:url value='loginForm.do'/>"; 
	</script>
</c:if> 
<c:if test="${status eq '-99'}">
	<script>
		alert("잘못된 접근입니다.");
		location.href="<c:url value='loginForm.do'/>"; 
	</script>
</c:if>  
<c:if test="${status eq '100'}">
	<script>
		alert("수정되었습니다.");  
		location.href="<c:url value='loginForm.do'/>"; 
	</script>
</c:if>  
<c:if test="${status eq '105'}">
	<script>
		alert("수정되었습니다.");  
		location.href="<c:url value='admin/userList.do'/>"; 
	</script>
</c:if>  
 