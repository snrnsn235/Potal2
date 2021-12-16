<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
</head>
<body>
<jsp:include page="menu.jsp"/>
<div align="center">
	<div><h1>Welcome to my Web-Site</h1></div>
	<button type="button" onclick="memberList()">멤버리스트 ajax 호출하기</button>
	<div id = "memberlist"></div> <!-- 호출결과를 여기서 출력한다. -->
</div>

<script type="text/javascript">
	function memberList() {
		$.ajax( {
			   url: "ajaxMemberList.do",
			   type:"post",
			   dataType: "json",
			   success: function(data) { //넘어온 데이터를 가공해서 원하는 곳에 출력해준다.
				   console.log(data); 
			   // 데이터 가공하기
			  // $.each(data) = function name() {
				
			//}
			      },
			   error : function(){
			   }
			});
	}
</script>
</body>
</html>