<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<c:if test="${user == null }">
	<script>
		toLoginForm();
	</script>
</c:if>
<c:choose>
	<c:when test = "${type == 'write'}"> 
		<c:set var="url" value="/admin/insertExhibit.do" /> 
		<c:set var="btn_txt" value="등록" /> 
	</c:when>
	<c:otherwise> 
		 <c:set var="url" value="/admin/updateExhibit.do?id=${map.id}" /> 
		<c:set var="btn_txt" value="수정" /> 
	</c:otherwise>	
</c:choose>

<div class="container">  
<br/>
<br/>
	<form id="frm" name="frm" method="post" enctype="multipart/form-data"> 
		<label for="admin"><h5>available</h5></label>&nbsp;&nbsp;  
	    <c:choose>
	    	<c:when test="${map!=null and map.AVAILABLE != true}">
	    		<input type="checkbox" name="available"  value="available"> 
	    	</c:when>
	    	<c:otherwise>
	    		<input type="checkbox" name="available"  value="available" checked> 
	    	</c:otherwise>
	    </c:choose>
		<input type="hidden" name="url" id="url" value="<c:url value='${url}'/>">
		<br/> 
		<div class="form-group">
		    <label for="start_date"><strong>Start Date</strong></label> 
		    <input type="date" name="start_date" id="start_date"  value="${map.start_date}" required> &nbsp;&nbsp;
		    <label for="end_date"><strong>End Date</strong></label>
		    <input type="date" name="end_date" id="end_date"  value="${map.end_date}" required> 
	    </div>
		<div class="form-group">
	    	<label for="title"><h4>Title</h4></label>
	    	<input type="text" class="form-control" id="title" name ="title" required value="${map.title}">
	 	</div>
		  
	    
	  	<div class="form-group">
		    <label for="descript"><h4>Description</h4></label>
		    <textarea class="form-control" id="descript" name="descript" 
		    		  style="height:150px;"
		    		  required
		    		  placeholder="최대 500자">${map.descript}</textarea> 
	  	</div>  
	  	<div class="form-group">
	    	<label for="price"><h4>Price</h4></label>
	    	<input type="number" class="form-control" id="price" name="price" value="${map.price}"> 
	  	</div>   
	  	<div class="form-group">
	    	<label for="image"><h4>ImageFile</h4></label>
	    	<input type="file" class="form-control" id="image" name="image">
	    	<input type="hidden" value="${map.image}" id="original_file" name="original_file"> 
	  	</div>   
	 </form>
	  	<button class="btn btn-primary" onclick="check_date()" id="submit_btn">${btn_txt}</button>&nbsp;
	  	<a href="<c:url value='/exhibitList.do' />" class="btn btn-secondary">목록으로</a>
	 
	 <script>
	 	function check_date(){
	 		var f = document.frm
	 		var url = document.frm.url.value; 
	 		var start_date = new Date(f.start_date.value).getTime(); 
	 		var end_date = new Date(f.end_date.value).getTime(); 
	 		var descript = f.descript;
	 		var type = document.getElementById("submit_btn").innerText;
	 		
	 		console.log(type);
	 		f.action = url; 
	 		
	 		if($("#start_date").val() == "" || $("#end_date").val() == ""){
	 			alert("날짜를 입력해주세요");
	 			return;
	 		}
	 		if($("#title").val() == "" ){
	 			alert("제목을 입력해주세요");
	 			$("#title").focus();
	 			return;
	 		}
	 		if($("#descript").val() == "" ){
	 			alert("내용을 입력해주세요");
	 			$("#descript").focus();
	 			return;
	 		}
	 		if($("#price").val() == "" ){
	 			alert("가격을 입력해주세요");
	 			$("#price").focus();
	 			return;
	 		}
	 		if(type!="수정"){
		 		if($("#image").val() == "" ){
		 			alert("포스터를 업로드해주세요");
		 			$("#image").focus();
		 			return;
		 		} 
	 		}
	 		
	 		if((end_date-start_date)<=0){
	 			alert("종료일은 시작일보다 나중이어야합니다."); 
	 		}else if(descript.value.length>500){
	 			console.log(descript.value.length);
	 			alert("500자까지만 입력 가능합니다.");
	 			descript.focus();
	 			
	 		}else{
	 			f.submit();
	 		}
	 		
	 	}
	 </script>  
</div>
 