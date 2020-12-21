<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%-- <jsp:forward page="/exhibitList.do"/>--%>
<div class="intro"> 
    <h4>
	  	<svg width="1.2em" height="1.2em" viewBox="0 0 16 16" class="bi bi-easel" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
		  	<path d="M8.473.337a.5.5 0 0 0-.946 0L6.954 2h2.092L8.473.337zM12.15 11h-1.058l1.435 4.163a.5.5 0 0 0 .946-.326L12.15 11zM8.5 11h-1v2.5a.5.5 0 0 0 1 0V11zm-3.592 0H3.85l-1.323 3.837a.5.5 0 1 0 .946.326L4.908 11z"/>
		  	<path fill-rule="evenodd" d="M14 3H2v7h12V3zM2 2a1 1 0 0 0-1 1v7a1 1 0 0 0 1 1h12a1 1 0 0 0 1-1V3a1 1 0 0 0-1-1H2z"/>
	    </svg>
		<strong>전시간 All-Time</strong> <br/>
		<small>한눈에 보는 전시간의 전시</small> 
	</h4>
</div>
<style>
	header{
		display:none;
	}
	.intro{
		width:70%; 
		text-align:center;
		margin:auto;
		margin-top:10%;
		display:none;
	}
	
</style>
<script>
	$(document).ready(function(){ 
		$(".intro").fadeIn(2000,function(){
			location.href="<c:url value='/exhibitList.do'/>"; 
		});
	});
</script>